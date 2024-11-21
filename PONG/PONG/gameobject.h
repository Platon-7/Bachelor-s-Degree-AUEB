#pragma once
#include "util.h"

class Collidable// collidable gia tous diskous
{
public:
	virtual Disk getCollisionHull() const = 0;

};
class Collidable2 {// collidable gia ta rectangle
public:
	virtual Rectangle getCollisionRect() const = 0;
};


class GameObject {// gameobject h klash poy thn klhronomoyn sxedon oles oi alles klaseis px ball,barrier,player klp
protected:
	const class Game& game;
public:
	GameObject(const class Game& mygame);
	virtual void update() = 0;
	virtual void draw() = 0;
	virtual void init() = 0;
};