package com.leont.jumpingman;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

import sun.rmi.runtime.Log;

public class JumpingMan extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture marios;
	Texture marior;
	Texture[] t;
	Texture[] t2;
	Texture trap;
	Texture trap2;
	Texture birds1;
	Texture birds2;
	Texture gameover;
	int clock = 0;
	ShapeRenderer shapeRenderer;
	float MarioY = 0;
	float velocity = 0;
	float gravity = 2;
	int moveB = 20;
	int trapx = 0;
	int trapx2 = 200;
	int flyx = 5000;
	Circle cer;
	Circle cer2;
	Circle cer3;
	Rectangle rec;
	int gameState = 0;

	@Override
	public void create() {

		shapeRenderer = new ShapeRenderer();
		gameover = new Texture("gameover.png");
		cer = new Circle();
		cer2 = new Circle();
		cer3 = new Circle();
		rec = new Rectangle();
		batch = new SpriteBatch();
		background = new Texture("background.jpg");
		marios = new Texture("mario0 (1).gif");
		marior = new Texture("mario1 (1).gif");
		birds1 = new Texture("birds1.png");
		birds2 = new Texture("birds2.png");
		t = new Texture[2];
		t[0] = marios;
		t[1] = marior;
		t2 = new Texture[2];
		t2[0] = birds1;
		t2[1] = birds2;
		trap = new Texture("trap.png");
		trap2 = new Texture("trap.png");
	}

	@Override
	public void render() {

		Random r = new Random();
		int a = r.nextInt(Gdx.graphics.getWidth() / 2);
		int b = r.nextInt(Gdx.graphics.getWidth() * 2);
		batch.begin();
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		if (gameState == 0) {


			batch.draw(t[clock], Gdx.graphics.getWidth() / 2, MarioY);
			if (Gdx.input.justTouched()) {
				gameState = 1;
			}
		}
		if (gameState == 1) {



			batch.draw(t[clock], Gdx.graphics.getWidth() / 2, MarioY);
			batch.draw(trap, trapx, -50);
			batch.draw(trap2, trapx2, -50);
			batch.draw(t2[clock], flyx, Gdx.graphics.getHeight() / 2 + 100);
			if (Gdx.input.justTouched()) {
				if (MarioY == 0 || MarioY < Gdx.graphics.getHeight() / 3)
					velocity = -58;
			}
			velocity = velocity + gravity;
			if (MarioY > 0 || velocity < 0) {
				MarioY -= velocity;
			}
			if (MarioY < 0)
				MarioY = 0;
			velocity++;
			if (clock == 0) {
				clock = 1;

			} else clock = 0;
			trapx -= 10;
			trapx2 -= 10;
			if (trapx < 0) {
				trapx = Gdx.graphics.getWidth();
			}
			if (trapx2 < 0) {
				trapx2 = Gdx.graphics.getWidth() + a;
			}
			flyx -= 25;
			if (flyx < 0) {
				flyx = Gdx.graphics.getWidth() + b;
			}

		}
		if (gameState == 2) {

			batch.draw(gameover, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);



		}

		//shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		//shapeRenderer.setColor(Color.RED);
		cer.set(trapx + 132, 5, trap.getWidth() / 2 - 100);
		cer2.set(trapx2 + 132, 5, trap2.getWidth() / 2 - 100);
		cer3.set(flyx + 70, Gdx.graphics.getHeight() / 2 + 120, birds1.getWidth() / 2);
		//shapeRenderer.circle(cer.x,cer.y,cer.radius);
		//shapeRenderer.circle(cer2.x,cer2.y,cer2.radius);
		//shapeRenderer.circle(cer3.x,cer3.y,cer3.radius);
		rec = new Rectangle(Gdx.graphics.getWidth() / 2 + 10, MarioY, marios.getWidth(), marior.getHeight());
		//shapeRenderer.rect(Gdx.graphics.getWidth()/2+10,MarioY,marios.getWidth(),marior.getHeight());
		if (Intersector.overlaps(cer, rec) || Intersector.overlaps(cer2, rec) || Intersector.overlaps(cer3, rec)) {
			gameState = 2;
			batch.draw(gameover, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
			if (Gdx.input.justTouched()) {

				flyx=Gdx.graphics.getWidth();
				trapx=Gdx.graphics.getWidth();
				trapx2=Gdx.graphics.getWidth();
gameState=1;
			}

		}
		//shapeRenderer.end();
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		background.dispose();
	}


}

