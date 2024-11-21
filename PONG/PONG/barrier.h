#pragma once
#include "gameobject.h"
#include "config.h"

class Barrier : public GameObject, public Collidable2 { // ginetai kai xwris klhronomikothta thn proteinoyme omws
	graphics::Brush br;
	float pos_x;// otan den dinw arxikes times to programma crasharei ara dinw edw tyxaies times stis metablhtes kai outws h allws sthn init() tha allaksoyn se tuxaies ypologismenes apo th rangeRandom
	float pos_y;
	float width;
	float height;
public:
	Barrier(const class Game& mygame);
	void update() override;
	void draw() override;
	void init() override;
	void fixxxer(float a, float b);
	float getBarrierPosY() { return pos_y; }
	float getBarrierPosX() { return pos_x; }
	float getBarrierWidth() { return width; }
	float getBarrierHeight() { return height; }
	Rectangle getCollisionRect() const override;

};