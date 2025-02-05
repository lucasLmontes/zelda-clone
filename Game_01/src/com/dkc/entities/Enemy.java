package com.dkc.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.dkc.main.Game;
import com.dkc.world.Camera;
import com.dkc.world.World;

public class Enemy extends Entity {
	
	private double spd = 1;
	private int maskx = 3, masky = 5, maskw = 10, maskh = 10;
	
	private BufferedImage[] sprites;
	private int frames = 0, maxFrames = 20, index = 0, maxIndex = 1;

	public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, null);
		sprites = new BufferedImage[2];
		sprites[0] = Game.spritesheet.getSprite(96, 16, 16, 16);
		sprites[1] = Game.spritesheet.getSprite(96+16, 16, 16, 16);
	}
	
	public void tick() {
		if((int)x < Game.player.getX() && World.isFree((int)(x+spd), this.getY())
				&& !isColliding((int)(x+spd), this.getY())) {
			x += spd;
		} else if((int)x > Game.player.getX() && World.isFree((int)(x-spd), this.getY())
				&& !isColliding((int)(x-spd), this.getY())) {
			x -= spd;
		}
		
		if((int)y < Game.player.getY() && World.isFree(this.getX(), (int)(y+spd))
				&& !isColliding(this.getX(), (int)(y+spd))) {
			y += spd;
		} else if((int)y > Game.player.getY() && World.isFree(this.getX(), (int)(y-spd))
				&& !isColliding(this.getX(), (int)(y-spd))) {
			y -= spd;
		}
		
		frames++;
		if(frames > maxFrames) {
			frames = 0;
			index++;
			if(index > maxIndex) {
				index = 0;
			}
		}
	}
	
	public boolean isColliding(int xnext, int ynext) {
		Rectangle enemyCurrent = new Rectangle(xnext + maskx, ynext + masky, maskw, maskh);
		for(int i = 0; i < Game.enemies.size(); i++) {
			Enemy e = Game.enemies.get(i);
			if(e == this) {
				continue;
			}
			Rectangle targetEnemy = new Rectangle(e.getX() + maskx, e.getY() + masky, maskw, maskh);
			if(enemyCurrent.intersects(targetEnemy)) {
				return true;
			}
		}
		
		return false;
	}
	
	public void render(Graphics g) {
		g.drawImage(sprites[index], this.getX() - Camera.x, this.getY() - Camera.y, null);

		//g.fillRect(this.getX() - Camera.x + maskx, this.getY() - Camera.y + masky, maskw, maskh);
		//g.setColor(Color.blue);
	}

}
