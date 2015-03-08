package org.rency.utils.tools;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;

import org.rency.utils.exceptions.CoreException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CutPicture {
	
	private static final Logger logger = LoggerFactory.getLogger(CutPicture.class);

	/**
	 * @desc 全屏拍照
	 * @date 2015年1月6日 下午3:07:02
	 * @param suffix
	 * @throws CoreException
	 */
	public static String generatePicture(String suffix) throws CoreException{
		try {
			String filePath = System.getProperty("user.dir")+"/"+System.currentTimeMillis()+"."+suffix;
			Robot robot = new Robot();
			robot.delay(10000);
			Dimension dimension = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
			int width = (int) dimension.getWidth();
			int height = (int) dimension.getHeight();
			robot.keyRelease(KeyEvent.VK_F11);
			robot.delay(2000);
			Image image = robot.createScreenCapture(new Rectangle(0,0,width,height));
			BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = bi.createGraphics();
			g.drawImage(image, 0, 0, width, height, null);
			ImageIO.write(bi, suffix, new File(filePath));
			return filePath;
		} catch (MalformedURLException e) {
			logger.error("全屏拍照异常.",e);
			e.printStackTrace();
			throw new CoreException(e);
		} catch (IOException e) {
			logger.error("全屏拍照异常.",e);
			e.printStackTrace();
			throw new CoreException(e);
		} catch (AWTException e) {
			logger.error("全屏拍照异常.",e);
			e.printStackTrace();
			throw new CoreException(e);
		}		
	}	
}