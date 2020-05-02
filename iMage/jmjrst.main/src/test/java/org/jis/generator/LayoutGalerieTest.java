package org.jis.generator;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.*;
import java.nio.file.attribute.PosixFilePermission;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;


import static org.junit.Assert.*;

public class LayoutGalerieTest {
	
	private LayoutGalerie galerieUnderTest;
	private File fromFile;
	private File toFile;

	/**
	 * file for testing {@link org.jis.generator.LayoutGalerie#copyFile(File, File)} with temporary files
	 */
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	/**
	 * Before-Test method for {@link org.jis.generator.LayoutGalerie#copyFile(File, File)}.
	 */
	@Before
	public final void setUp() {
		galerieUnderTest = new LayoutGalerie(null, null);
	}

	/**
	 * After-Test method for {@link org.jis.generator.LayoutGalerie#copyFile(File, File)}.
	 */
	@After
	public final void tearDown() {
		galerieUnderTest = null;
	}

	/**
	 * Test method {@link org.jis.generator.LayoutGalerie#copyFile(File, File)}. with a folder instead of file
	 */
	@Test
	public final void testCopyFileWithFolder() throws IOException {
		File createdFile = folder.newFile("myfile.txt");
		File createdFolder = folder.newFolder("subfolder");
		assertThrows(FileNotFoundException.class, () -> {
			galerieUnderTest.copyFile(createdFolder, createdFile);
		});
	}

	/**
	 * Test method for {@link org.jis.generator.LayoutGalerie#copyFile(File, File)}. with not existing file
	 */
	@Test
	public final void testCopyFileWithNotExistingFile() throws IOException {
		File createdFile = new File("myfile1.txt");
		File createdFile2 = folder.newFile("myfile2.txt");
		assertThrows(FileNotFoundException.class, () -> {
			galerieUnderTest.copyFile(createdFile, createdFile2);
		});
	}

	/**
	 * Test method for {@link org.jis.generator.LayoutGalerie#copyFile(File, File)}. with a fromFile with no write permission
	 */
	@Test
	public final void testCopyWithLockedToFile() throws IOException, ExecutionException, InterruptedException {
		toFile = folder.newFile("to.txt");
		fromFile = folder.newFile("from.txt");
		Path toPath = FileSystems.getDefault().getPath(toFile.getPath());
		Path fromPath = FileSystems.getDefault().getPath(fromFile.getPath());
		Files.writeString(toPath, "pupa");
		Files.writeString(fromPath, "lupa");

		Set permissions = new HashSet<>(Files.getPosixFilePermissions(toPath));
		permissions.remove(PosixFilePermission.OWNER_WRITE);
		permissions.remove(PosixFilePermission.GROUP_WRITE);
		permissions.remove(PosixFilePermission.OTHERS_WRITE);

		Files.setPosixFilePermissions(toPath, permissions);

		assertThrows(IOException.class, () -> {
			galerieUnderTest.copyFile(fromFile, toFile);
		});
	}

	/**
	 * Test method for {@link org.jis.generator.LayoutGalerie#copyFile(File, File)}. with no read permission
	 */
	@Test
	public final void testCopyWithLockedFromFile() throws IOException {
		toFile = folder.newFile("to.txt");
		fromFile = folder.newFile("from.txt");
		Path toPath = FileSystems.getDefault().getPath(toFile.getPath());
		Path fromPath = FileSystems.getDefault().getPath(fromFile.getPath());
		Files.writeString(toPath, "pupa");
		Files.writeString(fromPath, "lupa");

		Set permissions = new HashSet<>(Files.getPosixFilePermissions(fromPath));
		permissions.remove(PosixFilePermission.OWNER_READ);
		permissions.remove(PosixFilePermission.GROUP_READ);
		permissions.remove(PosixFilePermission.OTHERS_READ);

		Files.setPosixFilePermissions(fromPath, permissions);


		assertThrows(IOException.class, () -> {
			galerieUnderTest.copyFile(fromFile, toFile);
		});
	}

	/**
	 * Test method for {@link org.jis.generator.LayoutGalerie#copyFile(File, File)}. with coping to an existing file
	 */
	@Test
	public final void testCopyToExistingFile() throws IOException {
		toFile = folder.newFile("to.txt");
		fromFile = folder.newFile("from.txt");
		Path toPath = FileSystems.getDefault().getPath(toFile.getPath());
		Path fromPath = FileSystems.getDefault().getPath(fromFile.getPath());
		Files.writeString(toPath, "pupa");
		Files.writeString(fromPath, "lupa");
		galerieUnderTest.copyFile(fromFile, toFile);
		String contentsFrom = Files.readString(fromPath);
		String contentsTo = Files.readString(toPath);
		assertEquals(contentsFrom, contentsTo);
	}

	/**
	 * Test method for {@link org.jis.generator.LayoutGalerie#copyFile(File, File)}.
	 */
	@Test
	public final void testCopyFile() throws URISyntaxException {
		try {
			final File resourceFolder = new File(this.getClass().getResource(File.separator).toURI());
			fromFile = new File(resourceFolder, "from");
			toFile = new File(resourceFolder, "to");
			
			byte[] array = new byte[10];
			new Random().nextBytes(array);
			String randomString = new String(array);
		 			 
			fromFile.createNewFile();
			Path fromPath = FileSystems.getDefault().getPath(fromFile.getPath());
			Files.writeString(fromPath, randomString);
			 
			galerieUnderTest.copyFile(fromFile, toFile);
			 
			assertTrue(toFile.exists());
			 
			Path toPath = FileSystems.getDefault().getPath(toFile.getPath());
			String contents = Files.readString(toPath);
			 		 
			assertEquals(randomString, contents);
		 }
		 catch (IOException | URISyntaxException e) {
			fail();
		 }
		
	}

}
