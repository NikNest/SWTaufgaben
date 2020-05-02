package org.jis.generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

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
	 * Test method for {@link org.jis.generator.LayoutGalerie#copyFile(File, File)}.
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
	 * Test method for {@link org.jis.generator.LayoutGalerie#copyFile(File, File)}.
	 */
	@Test
	public final void testCopyFileWithNotExistingFile() throws IOException {
		File createdFile = new File("myfile1.txt");
		File createdFile2 = folder.newFile("myfile2.txt");
		assertThrows(FileNotFoundException.class, () -> {
			galerieUnderTest.copyFile(createdFile, createdFile2);
		});
	}

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
