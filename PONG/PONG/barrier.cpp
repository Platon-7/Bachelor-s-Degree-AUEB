#pragma once
#include "graphics.h"
#include "game.h"
#include "barrier.h"
#include "util.h"
#include "ball.h"

Barrier::Barrier(const Game& mygame)
	: GameObject(mygame)
{
	init();
}

void Barrier::update()
{
// h update den exei kapoia xrhsh giati to barrier apla kanei spawn kai ginetai destroy se xronika diasthmata tyxaia poy orizontai sth game.cpp


}

void Barrier::draw()
{
	graphics::Brush br;
	br.texture = "";
	br.fill_color[0] = 1.0f;
	br.fill_color[1] = 1.0f;
	br.fill_color[2] = 1.0f;
	br.fill_opacity = 1.0f;
	graphics::drawRect(pos_x, pos_y, width, height, br);

}


void Barrier::init()
{
	width = rangeRandom(30, 100);
	height = rangeRandom(40, 150);
	pos_x = (float)rangeRandom(250, 750);// pairnw tyxaia megethi kai tyxaies theseis gia thn mpala 
	pos_y = (float)rangeRandom(100, CANVAS_HEIGHT - 100);


}


Rectangle Barrier::getCollisionRect() const
{
	Rectangle rect;
	rect.rx = pos_x;
	rect.ry = pos_y;
	rect.rw = width;
	rect.rh = height;
	return rect;
}
void Barrier::fixxxer(float ball_x, float ball_y)
{
	if (ball_x >= pos_x - width/2 && ball_x <= pos_x + width/2 && ball_y >= pos_y - height/2 && ball_y <= pos_y + height/2) {
		width = rangeRandom(30, 100);
		height = rangeRandom(40, 150);
		pos_x = (float)rangeRandom(250, 750);// pairnw tyxaia megethi kai tyxaies theseis gia thn mpala 
		pos_y = (float)rangeRandom(100, CANVAS_HEIGHT - 100);
	}


}
