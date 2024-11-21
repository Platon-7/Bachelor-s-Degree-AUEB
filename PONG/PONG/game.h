#pragma once
#include "player.h"
#include "player2.h"
#include "ball.h"
#include "config.h"
#include "barrier.h"
class Game 
{
	Player* player = nullptr;// arxikopoiw toys constructors
	Player2* player2 = nullptr;
	Barrier* barrier = nullptr;
	Ball* ball = nullptr;
	float x = 10000;// metablhth poy bohthaei sto spawn toy barrier
	bool barrier_here = false;// metablhth poy elegxei an yparxei barrier 
	bool victory_sound = true;// o hxos otan teleiwnei to paixnidi
	graphics::Brush br;
	bool end_game = false;// elegxoi gia thn katastash toy paixnidioy
	bool game_has_begun = false;
	bool play_music = true;
	typedef enum{STATUS_START, STATUS_PLAYING, STATUS_EXIT} status_t;//3 katastaseis arxikh, paixnidi, end screen
	status_t status = STATUS_START;// h metablhth status tha pairnei mia apo tis parapanw
	bool player_initialized = false;
	bool player2_initialized = false;
	int player1points = 0;// oi pontoi twn paiktwn
	int player2points = 0;
	void spawnBall();// synarthseis poy kalw sth game.cpp
	bool checkCollision();
	void checkScore();
	void updateStartScreen();
	void updatePlayingScreen();
	void updateEndGame();
	void drawStartScreen();
	void drawPlayingScreen();
	void drawEnd();
	void spawnBarrier();
	bool checkForBarrier(float prev_ball_y,float prev_ball_x);
public: 
	void update();
	void draw();
	void init();// gia na ksekinhsw ta dedomena, mporei na mhn xreiastei
	Game();// default constructor
	~Game();// default destructor
};