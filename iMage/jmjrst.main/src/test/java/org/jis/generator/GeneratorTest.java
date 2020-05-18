package org.jis.generator;

import org.junit.*;
import org.junit.rules.TemporaryFolder;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.junit.Assert.*;

public class GeneratorTest {
  /**
   * Class under test.
   */
  private Generator generator;
  private int imageHeight, imageWidth;
  private static final File TEST_DIR = new File("target/test");
  private static final String IMAGE_FILE = "/image.jpg";
  private String imageName;

  /**
   * Input for test cases
   */
  private BufferedImage testImage;
  /**
   * Metadata for saving the image
   */
  private IIOMetadata imeta;
  /**
   * output from test cases
   */
  private BufferedImage rotatedImageTestResult;

  /**
   * Sicherstellen, dass das Ausgabeverzeichnis existiert und leer ist.
   */
  @BeforeClass
  public static void beforeClass() {
    if (TEST_DIR.exists()) {
      for (File f : TEST_DIR.listFiles()) {
        f.delete();
      }
    } else {
      TEST_DIR.mkdirs();
    }
  }

  @Before
  public void setUp() {
    this.generator = new Generator(null, 0);
    this.testImage = null;
    this.imeta = null;
    this.rotatedImageTestResult = null;
    
    final URL imageResource = this.getClass().getResource(IMAGE_FILE);
    imageName = extractFileNameWithoutExtension(new File(imageResource.getFile()));
   
    try (ImageInputStream iis = ImageIO.createImageInputStream(imageResource.openStream())) {
      ImageReader reader = ImageIO.getImageReadersByFormatName("jpg").next();
      reader.setInput(iis, true);
      ImageReadParam params = reader.getDefaultReadParam();
      this.testImage = reader.read(0, params);
      this.imageHeight = this.testImage.getHeight();
      this.imageWidth = this.testImage.getWidth();
      this.imeta = reader.getImageMetadata(0);
      reader.dispose();
    } catch (IOException e) {
      fail(e.getMessage());
    }
  }

  private String extractFileNameWithoutExtension(File file) {
    String fileName = file.getName();
    if (fileName.indexOf(".") > 0) {
      return fileName.substring(0, fileName.lastIndexOf("."));
    } else {
      return fileName;
    }
  }

  /**
   * Automatisches Speichern von testImage.
   */
  @After
  public void tearDown() {
    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd_HH.mm.ss.SSS");
    String time = sdf.format(new Date());

    File outputFile = new File(
        MessageFormat.format("{0}/{1}_rotated_{2}.jpg", TEST_DIR, imageName, time));

    if (this.rotatedImageTestResult != null) {
      try (FileOutputStream fos = new FileOutputStream(outputFile);
           ImageOutputStream ios = ImageIO.createImageOutputStream(fos)) {
        ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();
        writer.setOutput(ios);

        ImageWriteParam iwparam = new JPEGImageWriteParam(Locale.getDefault());
        iwparam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT); // mode explicit necessary

        // set JPEG Quality
        iwparam.setCompressionQuality(1f);
        writer.write(this.imeta, new IIOImage(this.rotatedImageTestResult, null, null), iwparam);
        writer.dispose();
      } catch (IOException e) {
        fail();
      }
    }
  }

  @Test
  public void testRotateImage_RotateImage0() {
    this.rotatedImageTestResult = this.generator.rotateImage(this.testImage, 0);

    assertTrue(imageEquals(this.testImage, this.rotatedImageTestResult));
  }

  @Test
  public void testRotateImage_RotateNull0() {
    this.rotatedImageTestResult = this.generator.rotateImage(null, 0);

    assertNull(this.rotatedImageTestResult);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRotateImage_Rotate042() {
    this.generator.rotateImage(this.testImage, 0.42);
  }

  @Test
  public void testRotateImage_Rotate90() {
    this.rotatedImageTestResult = this.generator.rotateImage(this.testImage, Generator.ROTATE_90);

    assertEquals(this.testImage.getHeight(), this.rotatedImageTestResult.getWidth());
    assertEquals(this.testImage.getWidth(), this.rotatedImageTestResult.getHeight());

    for (int i = 0; i < this.imageHeight; i++) {
      for (int j = 0; j < this.imageWidth; j++) {
        assertEquals(this.testImage.getRGB(j, i), this.rotatedImageTestResult.getRGB(this.imageHeight - 1 - i, j));
      }
    }
  }

  @Test
  public void testRotateImage_Rotate270() {
    this.rotatedImageTestResult = this.generator.rotateImage(this.testImage, Generator.ROTATE_270);

    assertEquals(this.testImage.getHeight(), this.rotatedImageTestResult.getWidth());
    assertEquals(this.testImage.getWidth(), this.rotatedImageTestResult.getHeight());

    for (int i = 0; i < this.imageHeight; i++) {
      for (int j = 0; j < this.imageWidth; j++) {
        assertEquals(this.testImage.getRGB(j, i), this.rotatedImageTestResult.getRGB(i, this.imageWidth - 1 - j));
      }
    }
  }

  @Test
  public void testRotateImage_RotateM90() {
    this.rotatedImageTestResult = this.generator.rotateImage(this.testImage, Math.toRadians(-90));

    assertEquals(this.testImage.getHeight(), this.rotatedImageTestResult.getWidth());
    assertEquals(this.testImage.getWidth(), this.rotatedImageTestResult.getHeight());

    for (int i = 0; i < this.imageHeight; i++) {
      for (int j = 0; j < this.imageWidth; j++) {
        assertEquals(this.testImage.getRGB(j, i), this.rotatedImageTestResult.getRGB(i, this.imageWidth - 1 - j));
      }
    }
  }

  @Test
  public void testRotateImage_RotateM270() {
    this.rotatedImageTestResult = this.generator.rotateImage(this.testImage, Math.toRadians(-270));

    assertEquals(this.testImage.getHeight(), this.rotatedImageTestResult.getWidth());
    assertEquals(this.testImage.getWidth(), this.rotatedImageTestResult.getHeight());

    for (int i = 0; i < this.imageHeight; i++) {
      for (int j = 0; j < this.imageWidth; j++) {
        assertEquals(this.testImage.getRGB(j, i), this.rotatedImageTestResult.getRGB(this.imageHeight - 1 - i, j));
      }
    }
  }

  /**
   * Check if two images are identical - pixel wise.
   * 
   * @param expected
   *          the expected image
   * @param actual
   *          the actual image
   * @return true if images are equal, false otherwise.
   */
  protected static boolean imageEquals(BufferedImage expected, BufferedImage actual) {
    if (expected == null || actual == null) {
      return false;
    }

    if (expected.getHeight() != actual.getHeight()) {
      return false;
    }

    if (expected.getWidth() != actual.getWidth()) {
      return false;
    }

    for (int i = 0; i < expected.getHeight(); i++) {
      for (int j = 0; j < expected.getWidth(); j++) {
        if (expected.getRGB(j, i) != actual.getRGB(j, i)) {
          return false;
        }
      }
    }

    return true;
  }

  /**
   * file for comfortable temporary files and folders creation
   */
  @Rule
  public TemporaryFolder folder = new TemporaryFolder();

  /**
   * checks if the input file not changed for width=0, height=0 params
   * @throws IOException if temp file could not be created
   */
  @Ignore
  @Test
  public void testGenerateTest() throws IOException {
    File originCopy = folder.newFile();
    File scaledOrigin = folder.newFile();
    File origin = new File("src/test/resources/image.jpg");
    Files.copy(origin.toPath(), originCopy.toPath(), StandardCopyOption.REPLACE_EXISTING);
    generator.generateText(originCopy, scaledOrigin, 0, 0);
    byte[] originContent = Files.readAllBytes(origin.toPath());
    byte[] unzippedContent = Files.readAllBytes(scaledOrigin.toPath());
    assertArrayEquals(originContent, unzippedContent);
  }

  /**
   * checks if the input file not changed for image=null params
   * @throws IOException if temp file could not be created
   */
  @Ignore
  @Test
  public void testGenerateSingle() throws IOException {
    File copyTo = folder.newFile();
    File origin = new File("src/test/resources/image.jpg");
    Files.copy(origin.toPath(), copyTo.toPath(), StandardCopyOption.REPLACE_EXISTING);
    BufferedImage generatedImg = null;
    generator.generateSingle(copyTo, null);
    byte[] originContent = Files.readAllBytes(origin.toPath());
    byte[] unzippedContent = Files.readAllBytes(copyTo.toPath());
    assertArrayEquals(originContent, unzippedContent);
  }

  /**
   * tests that the generate method will not delete
   * @throws IOException if temp file could not be created
   */
  @Ignore
  @Test
  public void testGenerateZipWithExistingZip() throws IOException {
    File tempZip = new File("src/test/resources/tempZip.zip");
    generator.generate(true);
    assertTrue(tempZip.exists());
  }

  /**
   * zip given images with generator.createZip than unzip it, than compare with zipped images
   * @throws IOException if temp file could not be created
   */
  @Ignore
  @Test
  public void testZipAFile() throws IOException {
    File copyTo = folder.newFile();
    File origin = new File("src/test/resources/image.jpg");
    File zipFile = folder.newFile();
    Files.copy(origin.toPath(), copyTo.toPath(), StandardCopyOption.REPLACE_EXISTING);
    Vector<File> images = new Vector<>();
    images.add(copyTo);
    generator.createZip(zipFile, images);
    //unzipping
    File unzipDir = folder.newFolder();
    byte[] buffer = new byte[1024];
    ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
    ZipEntry zipEntry = zis.getNextEntry();
    while (zipEntry != null) {
      String imgname = zipEntry.getName().split("/")[zipEntry.getName().split("/").length - 1];
      File newFile = new File(unzipDir, imgname);
      FileOutputStream fos = new FileOutputStream(newFile);
      int len;
      while ((len = zis.read(buffer)) > 0) {
        fos.write(buffer, 0, len);
      }
      fos.close();
      zipEntry = zis.getNextEntry();
    }
    zis.closeEntry();
    zis.close();
    //asserting
    assertEquals(1, unzipDir.listFiles().length);
    byte[] originContent = Files.readAllBytes(origin.toPath());
    byte[] unzippedContent = Files.readAllBytes(unzipDir.listFiles()[0].toPath());
    assertArrayEquals(originContent, unzippedContent);

  }

  /**
   * compare 4 times rotated in pi/2 im with the original img
   * @throws IOException if temp file could not be created
   */
  @Ignore
  @Test
  public void testRotateImg() throws IOException {
    File copyTo = folder.newFile();
    File origin = new File("src/test/resources/image.jpg");
    Files.copy(origin.toPath(), copyTo.toPath(), StandardCopyOption.REPLACE_EXISTING);

    //rotate img in 2pi rad
    generator.rotate(copyTo);
    generator.rotate(copyTo);
    generator.rotate(copyTo);
    generator.rotate(copyTo);
    byte[] originContent = Files.readAllBytes(origin.toPath());
    byte[] unzippedContent = Files.readAllBytes(copyTo.toPath());
    assertArrayEquals(originContent, unzippedContent);
  }

  /**
   * test image scalling
   * @throws IOException if temp file could not be created
   */
  @Ignore
  @Test
  public void testGenerateImg() throws IOException {
    File origin = new File("src/test/resources/image.jpg");
    File fileTo = folder.newFile();
    int width = 100;
    int height = 1040;
    generator.generateImage(origin, fileTo, false, width, height, "");
    BufferedImage bimg1 = ImageIO.read(origin);
    int originalWidth          = bimg1.getWidth();
    int originalHeight         = bimg1.getHeight();
    BufferedImage bimg2 = ImageIO.read(fileTo);
    int scaledWidth          = bimg2.getWidth();
    int scaledHeight         = bimg2.getHeight();
    int countedWidth = height * originalWidth / originalHeight;
    assertEquals(height, scaledHeight);
    assertEquals(countedWidth, scaledWidth);
  }
}
