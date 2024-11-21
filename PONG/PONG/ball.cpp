#include "ball.h"
#include "graphics.h"
#include <iostream>
#include "util.h"
#include <string>
#include <vecmath.h>
#include "game.h"
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <cstdlib>

void Ball::update()
{
	if (hits > x) {// kathe 10 xtyphmata anebainei h taxythta
		ball_speed = ball_speed * 1.5f;
		x = x + 10;
	}

	ball_future_center_x = ball_center_x + ball_direction_x * ball_speed;// me bash thn taxythta thn kateythinsh kai to x ths mpalas ypologizw thn epomenh thesh x
	ball_future_center_y = ball_center_y + ball_direction_y * ball_speed;// me bash thn taxythta thn kateythinsh kai to y ths mpalas ypologizw thn epomenh thesh y

	if ((ball_future_center_y + ball_radius) >= CANVAS_HEIGHT) {// an ftasei sto CANVAS_HEIGHT dld sto katw orio toy kamva tote kane reflect
		math::vec2 dir{ ball_direction_x, ball_direction_y };
		math::vec2 normal = { 0.0f, -1.0f };//h poreia poy tha parei h mpala meta thn kroush
		math::vec2 reflection = math::reflect(dir, normal);// h kroush
		ball_direction_x = reflection.x;
		ball_direction_y = reflection.y;

	}
	else if ((ball_future_center_y - ball_radius) <= 0.0f) {// an ftasei sto CANVAS_HEIGHT dld sto panw orio toy kamva tote kane reflect
		math::vec2 dir{ ball_direction_x, ball_direction_y };
		math::vec2 normal = { 0.0f, 1.0f };
		math::vec2 reflection = math::reflect(dir, normal);
		ball_direction_x = reflection.x;
		ball_direction_y = reflection.y;

	}

	ball_center_x += ball_direction_x * ball_speed;// prosarmose analoga tis syntetagmenes ths mpalas
	ball_center_y += ball_direction_y * ball_speed;

}
void Ball::hitBarrier(float barrier_x, float barrier_y,float barrier_width,float barrier_height)
{

	if (ball_center_x - ball_radius<barrier_x - barrier_width/2) {// an to mpalaki xtypaei thn aristera pleyra toy rect
		math::vec2 dir{ ball_direction_x, ball_direction_y };
		math::vec2 normal = { -1.0f, 0.0f };
		math::vec2 reflection = math::reflect(dir, normal);
		ball_direction_x = reflection.x;
		ball_direction_y = reflection.y;
		play_collision_sound = true;
	}
	else if (ball_center_x  + ball_radius>barrier_x + barrier_width/2){// an to mpalaki xtypaei thn deksia pleyra toy rect
		math::vec2 dir{ ball_direction_x,ball_direction_y };
		math::vec2 normal = { 1.0f, 0.0f };
		math::vec2 reflection = math::reflect(dir, normal);
		ball_direction_x = reflection.x;
		ball_direction_y = reflection.y;
		play_collision_sound = true;
	}

	else if (ball_center_y-ball_radius<barrier_y- barrier_height/2) {// an to mpalaki xtypaei thn panw pleyra toy rect
		math::vec2 dir{ ball_direction_x, ball_direction_y };
		math::vec2 normal = { 0.0f, -1.0f };
		math::vec2 reflection = math::reflect(dir, normal);
		ball_direction_x = reflection.x;
		ball_direction_y = reflection.y;
		play_collision_sound = true;
	}
	else if (ball_center_y+ball_radius>barrier_y+barrier_height/2) {// an to mpalaki xtypaei thn katw pleyra toy rect
		math::vec2 dir{ ball_direction_x, ball_direction_y };
		math::vec2 normal = { 0.0f, 1.0f };
		math::vec2 reflection = math::reflect(dir, normal);
		ball_direction_x = reflection.x;
		ball_direction_y = reflection.y;
		play_collision_sound = true;
	}


}
void Ball::hit()
{


	math::vec2 dir{ ball_direction_x, ball_direction_y };// gia na mpo edo shmainei oti me petyxe enas apo toys dyo paiktes ara kane reflect
	math::vec2 normal = { -1.0f, 0.0f };
	math::vec2 reflection = math::reflect(dir, normal);
	ball_direction_x = reflection.x;
	ball_direction_y = reflection.y;
	play_collision_sound = true;
	hits++;

	ball_center_x += ball_direction_x * ball_speed;// enhmerwnw tis syntetagmenes
	ball_center_y += ball_direction_y * ball_speed; 
	std::string wav = std::string(ASSET_PATH) + "pong_hit_edited.wav";
	graphics::playSound(wav, 0.5f);



}



void Ball::init()
{
	ball_radius = 4.0f;
	ball_speed = 1.5f;
	int u = 0;
	int min = 5;
	int max = 495;
	ball_center_x = CANVAS_WIDTH / 2;
	ball_center_y = rangeRandom(min, max);// thelw h mpala na arxikopoieitai ston aksona X stathera alla na mporei na bgei apo opoiodhpote shmeio ston aksona Y

	while (u == 0) {
		float local = randOper(0, 1);
		ball_direction_x = local * rand0to1();//ftiaxno tyxaies kateuthinseis alla an den einai kales gia na einai to paixnidi eyxaristo ths ksanaorizw 
		ball_direction_y = local * rand0to1();
		// Normalize
		float length = std::sqrt(ball_direction_x * ball_direction_x + ball_direction_y * ball_direction_y);
		ball_direction_x /= length;// metatrepw se monada kamva tis syntetagmenes
		ball_direction_y /= length;
		//an h mpala arxikopoihthei etsi wste na paizei pio poly me tous parallhloys aksones para me tous kathetous tote na tis dothoyn ksana times
		if (std::abs(ball_direction_y) - std::abs(ball_direction_x) <= 0.4) {
			u++;
		}
	}
}
void Ball::draw()
{
	br.fill_color[0] = 1.1f;// kanw draw thn mpala, den xreiazomai texture einai apla enas aspros diskos
	br.fill_color[1] = 1.1f;
	br.fill_color[2] = 1.1f;
	br.outline_opacity = 0.0f;
	graphics::drawDisk(ball_center_x, ball_center_y, ball_radius, br);

}


Disk Ball::getCollisionHull() const
{
	Disk disk;// to collision hull poy me bohthaei stis krouseis me barrier kai paiktes
	disk.cx = ball_center_x;
	disk.cy = ball_center_y;
	disk.radius = ball_radius;
	return disk;
}


Ball::Ball(const Game& mygame)
	: GameObject(mygame)
{
	init();// kalw amesws thn init otan ftiakso ena antikeimeno typoy Ball
}

Ball::~Ball()
{

}