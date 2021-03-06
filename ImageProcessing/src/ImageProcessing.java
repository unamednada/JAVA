import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import javax.imageio.ImageIO;
public class ImageProcessing {
	public static void main(String[] args) {
    // The provided images are apple.jpg, flower.jpg, and kitten.jpg
		int[][] imageData = imgToTwoD("./couple.jpg");
    // Or load your own image using a URL!
		//int[][] imageData = imgToTwoD("https://content.codecademy.com/projects/project_thumbnails/phaser/bug-dodger.png");
		//viewImageData(imageData);
		//int[][] trimmed = trimBorders(imageData, 60);
		//twoDToImage(trimmed, "./trimmed_couple.jpg");
		// int[][] allFilters = stretchHorizontally(shrinkVertically(colorFilter(negativeColor(trimBorders(invertImage(imageData), 50)), 200, 20, 40)));
		// Painting with pixels
		//int[][] negative = negativeColor(imageData);
		//twoDToImage(negative, "./negative_couple.jpg");
		
		//int[][] stretched = stretchHorizontally(imageData);
		//twoDToImage(stretched, "./stretched_couple.jpg");
		//int[][] shrunk = shrinkVertically(imageData);
		//twoDToImage(shrunk, "./shrunk_couple.jpg");
		//int[][] inverted = invertImage(imageData);
		//twoDToImage(inverted, "./inverted_couple.jpg");
		//int[][] filtered = colorFilter(imageData, -200, 100, -200);
		//twoDToImage(filtered, "./filtered_couple.jpg");
		int[][] random = paintRandomImage(imageData);
		twoDToImage(random, "./random_couple.jpg");
	}
	// Image Processing Methods
	public static int[][] trimBorders(int[][] imageTwoD, int pixelCount) {
		// Example Method
		if (imageTwoD.length > pixelCount * 2 && imageTwoD[0].length > pixelCount * 2) {
			int[][] trimmedImg = new int[imageTwoD.length - pixelCount * 2][imageTwoD[0].length - pixelCount * 2];
			for (int i = 0; i < trimmedImg.length; i++) {
				for (int j = 0; j < trimmedImg[i].length; j++) {
					trimmedImg[i][j] = imageTwoD[i + pixelCount][j + pixelCount];
				}
			}
			return trimmedImg;
		} else {
			System.out.println("Cannot trim that many pixels from the given image.");
			return imageTwoD;
		}
	}
	public static int[][] negativeColor(int[][] imageTwoD) {
		int height = imageTwoD.length;
		int width = imageTwoD[0].length;
		int[][] negativeImage = new int[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int[] rgba = getRGBAFromPixel(imageTwoD[i][j]);
				for (int k = 0; k < 3; k++) {
					rgba[k] = (255 - rgba[k]);
				}
				int hexa = getColorIntValFromRGBA(rgba);
				negativeImage[i][j] = hexa;
			}
		}
		return negativeImage;
	}
	public static int[][] stretchHorizontally(int[][] imageTwoD) {
		int height = imageTwoD.length;
		int width = imageTwoD[0].length;
		int[][] stretchedImage = new int[height][width * 2];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				stretchedImage[i][j * 2] = imageTwoD[i][j];
				stretchedImage[i][j * 2 + 1] = imageTwoD[i][j];
			}
		}
		return stretchedImage;
	}
	public static int[][] shrinkVertically(int[][] imageTwoD) {
		int height = imageTwoD.length;
		int width = imageTwoD[0].length;
		int[][] shrunkImage = new int[height / 2][width];
		for (int i = 0; i < height / 2; i++) {
			for (int j = 0; j < width; j++) {
				shrunkImage[i][j] = imageTwoD[i *2][j];
			}
		}
		return shrunkImage;
	}
	public static int[][] invertImage(int[][] imageTwoD) {
		int height = imageTwoD.length;
		int width = imageTwoD[0].length;
		int[][] invertedImage = new int[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				invertedImage[height - 1 - i][width - 1 - j] = imageTwoD[i][j];
			}
		}
		return invertedImage;
	}
	public static int[][] colorFilter(int[][] imageTwoD, int redChangeValue, int greenChangeValue, int blueChangeValue) {
		int height = imageTwoD.length;
		int width = imageTwoD[0].length;
		int[][] filteredImage = new int[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int[] rgba = getRGBAFromPixel(imageTwoD[i][j]);
				rgba[0] += redChangeValue;
				rgba[1] += greenChangeValue;
				rgba[2] += blueChangeValue;
				for (int k = 0; k < 3; k++) {
					if (rgba[k] > 255) {
						rgba[k] = 255;
					} else if (rgba[k] < 0) {
						rgba[k] = 0;
					}
				}
				int hexa = getColorIntValFromRGBA(rgba);
				filteredImage[i][j] = hexa;
			}
		}
		return filteredImage;
	}
	// Painting Methods
	public static int[][] paintRandomImage(int[][] canvas) {
		Random rand = new Random();
		int height = canvas.length;
		int width = canvas[0].length;
		int[][] randomImage = new int[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int[] rgba = new int[4];
				rgba[0] = rand.nextInt(256);
				rgba[1] = rand.nextInt(256);
				rgba[2] = rand.nextInt(256);
				rgba[3] = 255;
				int hexa = getColorIntValFromRGBA(rgba);
				randomImage[i][j] = hexa;
				}
		}
		return randomImage;
	}
		
		
	public static int[][] paintRectangle(int[][] canvas, int width, int height, int rowPosition, int colPosition, int color) {
		// TODO: Fill in the code for this method
		return null;
	}
	public static int[][] generateRectangles(int[][] canvas, int numRectangles) {
		// TODO: Fill in the code for this method
		return null;
	}
	// Utility Methods
	public static int[][] imgToTwoD(String inputFileOrLink) {
		try {
			BufferedImage image = null;
			if (inputFileOrLink.substring(0, 4).toLowerCase().equals("http")) {
				URL imageUrl = new URL(inputFileOrLink);
				image = ImageIO.read(imageUrl);
				if (image == null) {
					System.out.println("Failed to get image from provided URL.");
				}
			} else {
				image = ImageIO.read(new File(inputFileOrLink));
			}
			int imgRows = image.getHeight();
			int imgCols = image.getWidth();
			int[][] pixelData = new int[imgRows][imgCols];
			for (int i = 0; i < imgRows; i++) {
				for (int j = 0; j < imgCols; j++) {
					pixelData[i][j] = image.getRGB(j, i);
				}
			}
			return pixelData;
		} catch (Exception e) {
			System.out.println("Failed to load image: " + e.getLocalizedMessage());
			return null;
		}
	}
	public static void twoDToImage(int[][] imgData, String fileName) {
		try {
			int imgRows = imgData.length;
			int imgCols = imgData[0].length;
			BufferedImage result = new BufferedImage(imgCols, imgRows, BufferedImage.TYPE_INT_RGB);
			for (int i = 0; i < imgRows; i++) {
				for (int j = 0; j < imgCols; j++) {
					result.setRGB(j, i, imgData[i][j]);
				}
			}
			File output = new File(fileName);
			ImageIO.write(result, "jpg", output);
		} catch (Exception e) {
			System.out.println("Failed to save image: " + e.getLocalizedMessage());
		}
	}
	public static int[] getRGBAFromPixel(int pixelColorValue) {
		Color pixelColor = new Color(pixelColorValue);
		return new int[] { pixelColor.getRed(), pixelColor.getGreen(), pixelColor.getBlue(), pixelColor.getAlpha() };
	}
	public static int getColorIntValFromRGBA(int[] colorData) {
		if (colorData.length == 4) {
			Color color = new Color(colorData[0], colorData[1], colorData[2], colorData[3]);
			return color.getRGB();
		} else {
			System.out.println("Incorrect number of elements in RGBA array.");
			return -1;
		}
	}
	public static void viewImageData(int[][] imageTwoD) {
		if (imageTwoD.length > 3 && imageTwoD[0].length > 3) {
			int[][] rawPixels = new int[3][3];
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					rawPixels[i][j] = imageTwoD[i][j];
				}
			}
			System.out.println("Raw pixel data from the top left corner.");
			System.out.print(Arrays.deepToString(rawPixels).replace("],", "],\n") + "\n");
			int[][][] rgbPixels = new int[3][3][4];
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					rgbPixels[i][j] = getRGBAFromPixel(imageTwoD[i][j]);
				}
			}
			System.out.println();
			System.out.println("Extracted RGBA pixel data from top the left corner.");
			for (int[][] row : rgbPixels) {
				System.out.print(Arrays.deepToString(row) + System.lineSeparator());
			}
		} else {
			System.out.println("The image is not large enough to extract 9 pixels from the top left corner");
		}
	}
}