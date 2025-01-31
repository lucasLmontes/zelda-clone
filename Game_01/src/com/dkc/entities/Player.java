package com.dkc.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.dkc.main.Game;
import com.dkc.world.Camera;
import com.dkc.world.World;

public class Player extends Entity{
	public boolean right, left, up, down;
	
	private int right_dir = 0, left_dir = 1;
	private int dir = left_dir;
	private int frames = 0, maxFrames = 15, index = 0, maxIndex = 2;
	private boolean moved;
	
	public double spd = 1.4;
	public BufferedImage[] rightPlayer, leftPlayer;

	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		rightPlayer = new BufferedImage[3];
		leftPlayer = new BufferedImage[3];
		
		for(int i = 0; i < rightPlayer.length; i++) {
			rightPlayer[i] = Game.spritesheet.getSprite(32 + (i * 16), 0, 16, 16);
		}
		for(int i = 0; i < leftPlayer.length; i++) {
			leftPlayer[i] = Game.spritesheet.getSprite(32 + (i * 16), 16, 16, 16);
		}
	}
	
	public void tick() {
		moved = false;
		
		if(right && World.isFree((int)(x+spd), this.getY())) {
			moved = true;
			dir = right_dir;
			x += spd;
		} else if(left && World.isFree((int)(x-spd), this.getY())) {
			moved = true;
			dir = left_dir;
			x -= spd;
		}
		
		if(up && World.isFree(this.getX(), (int)(y-spd))) {
			moved = true;
			y -= spd;
		} else if(down && World.isFree(this.getX(), (int)(y+spd))) {
			moved = true;
			y += spd;
		}
		
		if(moved) {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index == maxIndex) {
					index = 0;
				}
			}
		} else {
			index = 0;
		}
		
		Camera.x = Camera.clamp(this.getX() - Game.WIDTH/2, 0, World.WIDTH*16 - Game.WIDTH);
		Camera.y = Camera.clamp(this.getY() - Game.HEIGHT/2, 0, World.HEIGHT*16 - Game.HEIGHT);
	}
	
	public void render(Graphics g) {
		if(dir == right_dir) {
			g.drawImage(rightPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		} else if(dir == left_dir) {
			g.drawImage(leftPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
	}

}
