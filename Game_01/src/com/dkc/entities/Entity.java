package com.dkc.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.dkc.main.Game;
import com.dkc.world.Camera;

public class Entity {
	protected int x, y;
	private int width, height;
	private BufferedImage sprite;
	
	public static BufferedImage LIFE_PACK_EN = Game.spritesheet.getSprite(5*16, 0, 16, 16);
	public static BufferedImage WEAPON_EN = Game.spritesheet.getSprite(6*16, 0, 16, 16);
	public static BufferedImage BULLET_EN = Game.spritesheet.getSprite(5*16, 16, 16, 16);
	public static BufferedImage ENEMY_EN = Game.spritesheet.getSprite(6*16, 16, 16, 16);

	public Entity(int x, int y, int width, int height, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, this.getX()- Camera.x, this.getY() - Camera.y, this.getWidth(), this.getHeight(), null);
	}
}
