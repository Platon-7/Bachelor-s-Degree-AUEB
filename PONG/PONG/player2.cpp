#pragma once
#include "player2.h"
#include "graphics.h"

Player2::Player2(const Game& mygame)
	: GameObject(mygame)
{
}

void Player2::update()
{

	if (graphics::getKeyState(graphics::SCANCODE_UP)) {// me thn idia filosofia me ton player1 antistoixw koympia gia thn kinhsh toy player2
		pos_y -= speed * graphics::getDeltaTime() / 10.0f;
	}
	if (graphics::getKeyState(graphics::SCANCODE_DOWN)) {
		pos_y += speed * graphics::getDeltaTime() / 10.0f;
	}
	if (pos_y > CANVAS_HEIGHT-20) pos_y = CANVAS_HEIGHT-20;
	if (pos_y < 20) pos_y = 20;
}

void Player2::draw()
{

	graphics::Brush br;
	br.texture = "";
	br.fill_color[0] = 1.0f;
	br.fill_color[1] = 1.0f;
	br.fill_color[2] = 1.0f;
	br.fill_opacity = 1.0f;
	graphics::drawRect(pos_x, pos_y, 5, 40, br);

}

void Player2::init()
{

}
Rectangle Player2::getCollisionRect() const
{
	Rectangle rect;
	rect.rx = pos_x;
	rect.ry = pos_y;
	rect.rw = 5;
	rect.rh = 40;
	return rect;
}
