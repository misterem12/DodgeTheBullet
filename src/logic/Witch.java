package logic;

import java.util.Random;

import bulletSpawner.BulletPattern;
import bulletSpawner.BulletSpawner;
import bulletSpawner.NormalPattern;
import bulletSpawner.SpreadPattern;
import graphics.DrawingUtility;
import graphics.IRenderableHolder;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import utilities.NoBulletSpawnerException;

public class Witch extends RangedEnemy {
	
	BulletPattern irregularPattern;
	Random random = new Random();
//	int frame;
	
	public Witch(float x, float y) {
		super(x, y, Math.PI, 7, 20);
		// TODO Auto-generated constructor stub
		this.hp = 5;
		this.givenExp = 30;
		this.bulletPattern = new NormalPattern(this, 5, 6000, BulletPattern.DEFAULT_BURST_DELAY);
		this.irregularPattern = new SpreadPattern(this, 3, 30, 3, 6000, BulletPattern.DEFAULT_BURST_DELAY);
		this.bulletSpawner = new BulletSpawner(this.bulletPattern);
		if(random.nextFloat() > 0.5){
			this.bulletSpawner = new BulletSpawner(irregularPattern);
		}
		setNewPoint();
//		new NormalBulletSpawner(this).start();
	}
	
	@Override
	void update() {
		
		if(!this.isDestroy() && walkTo(pointX, pointY)){
//			frame = 0;
			this.focusOnPlayer();
			try {
				this.spawnBullet(this.bulletPattern);
			} catch (NoBulletSpawnerException e) {
				// TODO Auto-generated catch block
				this.setNewPoint();
				if(random.nextFloat() > 0.5){
					this.bulletSpawner = new BulletSpawner(irregularPattern);
				}
			}
		}
//		else frame++;
	}
	
	@Override
	public synchronized void render(GraphicsContext gc) {
		// TODO Auto-generated method stub
		DrawingUtility.drawRotateAvatar(gc, this.getX(), this.getY(), this.getAngle(), IRenderableHolder.witchModel);
	}
	
}
