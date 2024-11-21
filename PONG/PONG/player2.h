#pragma once
#include "gameobject.h"
#include "config.h"

class Player2 : public GameObject, public Collidable2 { 
	float speed = 3.0f;
	float pos_x = CANVAS_WIDTH - 50, pos_y = CANVAS_HEIGHT / 2;
public:
	Player2(const class Game& mygame);
	void update() override;
	void draw() override;
	void init() override;
	Rectangle getCollisionRect() const override;
};