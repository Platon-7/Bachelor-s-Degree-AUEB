#pragma once
#include "gameobject.h"
#include "config.h"

class Player : public GameObject, public Collidable2 { // ginetai kai xwris klhronomikothta thn proteinoyme omws
	float speed = 3.0f;
	float pos_x = 50, pos_y = CANVAS_HEIGHT / 2;
public:
	Player(const class Game& mygame);
	void update() override;
	void draw() override;
	void init() override;
	float getPlayerPosY() { return pos_x; }
	float getPlayerPosX() { return pos_y; }// pairnw th thesh toy paikth se kathe update
	Rectangle getCollisionRect() const override;
	// ton paikth tha ton ksekinhsw sthn update
};