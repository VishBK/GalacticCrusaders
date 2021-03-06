import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

@SuppressWarnings("unused")
public class Spaceship {
	static BufferedImage shipImg;
	
	Rectangle body;
	Rectangle gunBody;
	Rectangle bullet;
	Color color;
	int bulletSpeed;
	int speed;
	int health;
	
	boolean isShoot;
	boolean isCollision;
	
	public Spaceship() {
		body    = new Rectangle (Panel.screenWidth/2, Panel.screenHeight - 20, 50, 15);
		gunBody = new Rectangle ((body.x + body.width/2) - 12/2, body.y - (25-body.height), 12, 25);
		bullet  = new Rectangle ((int)(gunBody.getCenterX()), (int)(gunBody.getY() - 2), 2, 10);
		bulletSpeed = 2;
		speed       = 2;
		health = 3;
		color = Color.RED;
	}
	
	public Spaceship (int bs, int s, int h, Color c) {
		body    = new Rectangle (Panel.screenWidth/2, Panel.screenHeight - 20, 50, 15);
		gunBody = new Rectangle ((int)(body.getCenterX() - 6), body.y - (25 - body.height), 12, 25);
		bullet  = new Rectangle ((int)(gunBody.getCenterX() - 1), (int)(gunBody.getY() + 2), 2, 10);
		bulletSpeed = bs;
		speed       = s;
		health = h;
		color = c;
	}
	
	public void draw (Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fill(bullet);
		g.setColor(color);
		g.fill(body);
		g.drawImage(shipImg, body.x, gunBody.y, body.width, gunBody.height, null);
		g.fill(gunBody);
	}
	
	public void update() {
		if (isShoot) {
			bullet.y -= bulletSpeed;
		}
		
		if (bullet.y + bullet.height < 0 || isCollision) {
			bullet.y = (int)(gunBody.getY() + 2);
			bullet.x = (int)(gunBody.getCenterX() - 1);
			isShoot = false;
		}
	}
	
	public void shoot() {
		isShoot = true;
	}
	
	public void reset() {
		body.x    = Panel.screenWidth/2;
		gunBody.x = (int)(body.getCenterX() - 6);
		bullet.y  = (int)(gunBody.getY() + 2);
		bullet.x  = (int)(gunBody.getCenterX() - 1);
	}
	
	public void addLives (int n) {
		health += n;
	}
	
	public void move (int multiplier) {
		body.x    += speed * multiplier;
		gunBody.x += speed * multiplier;
		if (isShoot == false) bullet.x  += speed * multiplier;
	}
}
