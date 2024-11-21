#pragma once
#include "gameobject.h"
#include "config.h"
#include "graphics.h"

class Ball : public GameObject, public Collidable {
	int x = 10;// metablhth poy tha bohthsei argotera sthn aykshsh ths taxythtas
	int hits = 0;// idios skopos me thn apo panw
	graphics::Brush br;
	float ball_center_x;
	float ball_center_y;
	float ball_direction_x;
	float ball_direction_y;
	float ball_radius;
	float ball_future_center_y;
	float ball_future_center_x;
	bool play_collision_sound;
	float ball_speed;

public:
	void update() override;
	void draw() override;
	void init() override;
	void hitBarrier(float barrier_x, float barrier_y,float barrier_width, float barrier_height);
	void hit();
	float getPosY() { return ball_center_y; }
	float getPosX() { return ball_center_x; }// moy epistrefoyn thn thesh ths mpalas me bash to teleytaio update poy egine
	Disk getCollisionHull() const override;

	Ball(const class Game& mygame);
	~Ball();
};