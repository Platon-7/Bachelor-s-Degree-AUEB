#include "game.h"
#include "graphics.h"
#include "config.h"
#include <vecmath.h>
#include "ball.h"
#include <math.h>
#include "util.h"
#include "barrier.h"
#include <algorithm>



void Game::spawnBall()// an den yparxei mpala kane spawn
{
	if (!ball) {
		ball = new Ball(*this);
	}
}
void Game::spawnBarrier() {// an den yparxei barrier kane spawn
	if (!barrier) {
		barrier = new Barrier(*this);
	}
}
void Game::checkScore()
{// ayksanw to score gia toys paiktes an "mphke goal"
	if (ball) {
		if (ball->getPosX() >= CANVAS_WIDTH) {
			player1points += 1;
			std::string wav = std::string(ASSET_PATH) + "point.wav";
			graphics::playSound(wav, 0.1f);

		}
		if (ball->getPosX() <= 0) {
			player2points += 1;
			std::string wav = std::string(ASSET_PATH) + "point.wav";
			graphics::playSound(wav, 0.1f);
		}
	}
}
bool Game::checkForBarrier(float prev_ball_x,float prev_ball_y) {//an yparxei barrier elegxw an einai sthn troxia ths mpalas kai an prepei na ginei collision ginetai
	if (!ball || !barrier) {// toy dinw san orisma tis prohgoymenes theseis giati yparxei periptwsh na ginei collision kai na mhn to antilhfthei logw update, etsi gyrnaw stis prohgoymenes
		return false;
	}
	Rectangle r3 = barrier->getCollisionRect();
	Disk d2 = ball->getCollisionHull();

	float testX = d2.cx;
	float testY = d2.cy;
	float rectangle_x= r3.rx - r3.rw / 2;
	float rectangle_y = r3.ry - r3.rh / 2;


	if (d2.cx < rectangle_x)
		testX = rectangle_x; //if ball is left
	else if (d2.cx > rectangle_x + r3.rw)
		testX = rectangle_x + r3.rw; //if ball is right
	if (d2.cy < rectangle_y)
		testY = rectangle_y;//if ball is above
	else if (d2.cy > rectangle_y + r3.rh)
		testY = rectangle_y + r3.rh;// if ball is below

	float distX = d2.cx - testX;
	float distY = d2.cy - testY;
	float distance = sqrt((distX * distX) + (distY * distY));


	if (distance <= d2.radius)// elegxos gia ton aristero
		return true;
	else {
		return false;
	}


}

bool Game::checkCollision()
{// elegxw pithano collision metaksy ths rectangle mparas kai toy ball, tha mporoyse na ginei kai me Line ball alla protimhsame to rectangle
	Rectangle r1 = player->getCollisionRect();
	Rectangle r2 = player2->getCollisionRect();
	Disk d1 = ball->getCollisionHull();

	// gia ton aristero

	float testX1 = d1.cx;
	float testY1 = d1.cy;
	float rectangle_x = r1.rx - r1.rw / 2;
	float rectangle_y = r1.ry - r1.rh / 2;

	if (d1.cx < rectangle_x)
		testX1 = rectangle_x; //if ball is left
	else if (d1.cx > rectangle_x + r1.rw)
		testX1 = rectangle_x + r1.rw; //if ball is right
	if (d1.cy < rectangle_y)
		testY1 = rectangle_y;//if ball is above
	else if (d1.cy > rectangle_y + r1.rh)
		testY1 = rectangle_y + r1.rh;// if ball is below


	float distX1 = d1.cx - testX1;
	float distY1 = d1.cy - testY1;
	float distance1 = sqrt((distX1 * distX1) + (distY1 * distY1));

	// gia ton deksi paikth

	float testX2 = d1.cx;
	float testY2 = d1.cy;
	float rectangle_x_beta = r2.rx - r2.rw / 2;
	float rectangle_y_beta = r2.ry - r2.rh / 2;


	if (d1.cx < rectangle_x_beta)
		testX2 = rectangle_x_beta; //if ball is left
	else if (d1.cx > rectangle_x_beta + r2.rw)
		testX2 = rectangle_x_beta + r2.rw; //if ball is right
	if (d1.cy < rectangle_y_beta)
		testY2 = rectangle_y_beta;//if ball is above
	else if (d1.cy > rectangle_y_beta + r2.rh)
		testY2 = rectangle_y_beta + r2.rh;// if ball is below

	float distX2 = d1.cx - testX2;
	float distY2 = d1.cy - testY2;
	float distance2 = sqrt((distX2 * distX2) + (distY2 * distY2));


	if (distance1 <= d1.radius)// elegxos gia ton aristero
		return true;
	else if (distance2 <= d1.radius)// elegxos gia ton deksi
		return true;
	else {
		return false;
	}

}

void Game::update()
{// xwrizw thn update se 3 ypo-update mia gia kathe katastash
	if (status == STATUS_START) {
		updateStartScreen();
	}
	else if(status == STATUS_EXIT){
		updateEndGame();
	}
	else {
		updatePlayingScreen();
	}


}
void Game::updateStartScreen() {
	if (play_music) {// anoigw to paixnidi kai arxizei h mousikh
		std::string ogg = std::string(ASSET_PATH) + "intro.ogg";
		graphics::playMusic(ogg, 0.1f,false,0);
		play_music = false;
	}
	if (graphics::getKeyState(graphics::SCANCODE_RETURN)) {// an pathsei o paikths RETURN tote ksekinaei to paixnidi
		status = STATUS_PLAYING;// allazw to status
		graphics::stopMusic();// stamataw th mousikh
		game_has_begun = true;
	}
	if (graphics::getKeyState(graphics::SCANCODE_ESCAPE)) {// an pathsei o paikths ESCAPE termatizei
		graphics::stopMusic();
		graphics::destroyWindow();
		exit(0);
	}

}
void Game::updatePlayingScreen() {
	if (game_has_begun) {// hxos poy ypodeiknyei thn enarksh toy paixnidioy
		std::string wav = std::string(ASSET_PATH) + "begin.wav";
		graphics::playSound(wav, 0.1f);
		game_has_begun = false;
	}
	if (!player_initialized)
	{
		player = new Player(*this);
		player_initialized = true;
	}
	if (!player2_initialized)
	{
		player2 = new Player2(*this);
		player2_initialized = true;
	}
	if (player)
		player->update();

	if (player2)
		player2->update();


	spawnBall();

	float prev_ball_y = ball->getPosY();// krataw tis prohgoymenes theseis ths mpalas
	float prev_ball_x = ball->getPosX();

	if (ball) {
		ball->update();
	}
	float curr_ball_y = ball->getPosY();// kai tis kainourgies twra poy egine update
	float curr_ball_x = ball->getPosX();

	if (graphics::getGlobalTime() > x) {// an exei perasei to xroniko diasthma x mpes edw
		if (!barrier_here) {// an den yparxei barrier ftiakse ena
			spawnBarrier();
			barrier_here = true;
		}else{// an yparxei katastrepse to
			delete barrier;
			barrier = nullptr;
			barrier_here = false;
		}
		x = x + rangeRandom(10000, 20000);// megalwse to x
	}
	if (barrier)
		barrier->fixxxer(ball->getPosX(), ball->getPosY());

	if (barrier) {// an yparxei barrier ypologise me bash to radius tou posous elegxous prepei na kaneis gia tis prohgoymenes theseis
		barrier->update();
		float dx = curr_ball_x - prev_ball_x;

		float dy = curr_ball_y - prev_ball_y;

		float len = dx * dx + dy * dy;
		int N_tests1 = (int)ceil(len / ((barrier->getBarrierWidth()) * (barrier->getBarrierWidth())));// elegxei kai tis 2 pleyres
		int N_tests2 = (int)ceil(len / ((barrier->getBarrierHeight()) * (barrier->getBarrierHeight())));

		int N_tests = max(N_tests1, N_tests2);// an px h mia xreiazetai 2 tests kai h allh 1 tote krata ta 2 tests

		for (int i = 0; i <N_tests; i++)

		{
			float s = (i + 0.5f) / N_tests;

			float x = s * (curr_ball_x - prev_ball_x) + prev_ball_x;

			float y = s * (curr_ball_y - prev_ball_y) + prev_ball_y;

			if (checkForBarrier(x, y))
				ball->hitBarrier(barrier->getBarrierPosX(), barrier->getBarrierPosY(),barrier->getBarrierWidth(), barrier->getBarrierHeight());
		}
	}

	if (checkCollision()) {// an egine collision me kapoion apo tous paiktes kalese th hit
		ball->hit();
	}
	int temp_player_1_points = player1points;// prohgoymena score
	int temp_player_2_points = player2points;

	checkScore();

	if (player1points != temp_player_1_points || player2points != temp_player_2_points) {// an ta twrina me ta prohgoymena score exoyn diafora tote mphke goal ara prepei na katastrepso thn mpala
		delete ball;
		ball = nullptr;

	}
	if (player1points == 3 || player2points == 3) {// an opoiosdhpote apo tous 2 paiktes eftase to orio pontwn tote to paixnidi teleiwnei
		status = STATUS_EXIT;
		end_game = true;
	}
}
void Game::updateEndGame() {
	if (victory_sound) {// victory sound!
		std::string wav = std::string(ASSET_PATH) + "tada.wav";
		graphics::playSound(wav, 0.1f);
		victory_sound = false;
	}
	if (graphics::getKeyState(graphics::SCANCODE_RETURN)) {// edw thelw na ksanarxisei to paixnidi giayto mhdenizw ta score kai sbhnw paiktes, to mpalaki einai hdh sbhsmeno giati gia na ertho edo mphke pontos
		delete player;
		player = nullptr;
		delete player2;
		player2 = nullptr;
		if (barrier) {
			delete barrier;
			barrier = nullptr;
		}

		status = STATUS_PLAYING;
		player_initialized = false;
		player2_initialized = false;
		barrier_here = false;
		player1points = 0;
		player2points = 0;
		game_has_begun = true;
		victory_sound = true;
	}
	if (graphics::getKeyState(graphics::SCANCODE_ESCAPE)) {// an o paikths den thelei na paiksei allo pataei ESCAPE kai to paixnidi lhgei
		graphics::stopMusic();
		graphics::destroyWindow();
		exit(0);
	}
}

void Game::draw()
{	
	if (status==STATUS_START) {
		drawStartScreen();
	}
	else if(status==STATUS_EXIT){
		drawEnd();
	}
	else {
		drawPlayingScreen();
	}
}

void Game::drawStartScreen() {// draw to intro tou paixnidioy

	br.texture = std::string(ASSET_PATH) + "arcade.png";
	graphics::drawRect(CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2, CANVAS_WIDTH, CANVAS_WIDTH, br);
	br.texture = "";

	char inst3[40];
	sprintf_s(inst3, "PONG");
	graphics::drawText(CANVAS_WIDTH / 2-40, CANVAS_HEIGHT / 2-165, 50 , inst3, br);
	
	char inst[40];
	sprintf_s(inst, "USE W S FOR LEFT PLAYER");
	graphics::drawText(CANVAS_WIDTH / 2 - 170, CANVAS_HEIGHT / 2 , 21, inst, br);

	char inst2[40];
	sprintf_s(inst2, "USE ARROWS FOR RIGHT PLAYER");
	graphics::drawText(CANVAS_WIDTH / 2+20, CANVAS_HEIGHT / 2, 21, inst2, br);

	char output[40];
	sprintf_s(output, "Press ENTER to start: ");
	graphics::drawText(CANVAS_WIDTH / 2-100, CANVAS_HEIGHT / 2-100, 30, output, br);

	char output2[40];
	sprintf_s(output2, "Press ESCAPE to exit: ");
	graphics::drawText(CANVAS_WIDTH / 2 - 100, CANVAS_HEIGHT / 2 - 50, 30, output2, br);


}
void Game::drawPlayingScreen() {

	br.texture = std::string(ASSET_PATH) + "pong_background.png";

	//draw background
	graphics::drawRect(CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2, CANVAS_WIDTH, CANVAS_WIDTH, br);
		//bazw ksana CANVAS_WIDTH sto 4o orisma gia na mhn prosarmozw thn eikona sta thelw moy alla na thn afhnw, akoma kai na ksefigei apo th xwrhtikothta toy tamplo, an thelo na to prosarmosw bazw CANVAS_HEIGHT

	br.texture = "";
	br.fill_color[0] = 1.0f;
	br.fill_color[1] = 1.0f;
	br.fill_color[2] = 1.0f;
	graphics::drawRect(CANVAS_WIDTH / 2, CANVAS_WIDTH / 2, 5, 1000, br);
	//draw player
	if (player)
		player->draw();
	//draw the second player
	if (player2)
		player2->draw();

	if (ball)
		ball->draw();

	if (barrier)
		barrier->draw();

	if (player && player2)// print tous pontous twn paiktwn
	{
		char info[10];
		char info_beta[10];
		sprintf_s(info, "%d", player1points);
		sprintf_s(info_beta, "%d", player2points);
		graphics::drawText(CANVAS_WIDTH / 2 - 200, 100, 100, info, br);
		graphics::drawText(CANVAS_WIDTH / 2 + 200, 100, 100, info_beta, br);
	}


}
void Game::drawEnd() {

	char gameover[20];
	sprintf_s(gameover, "GAME OVER ");
	graphics::drawText(CANVAS_WIDTH / 2 - 100, CANVAS_HEIGHT / 2 - 150, 80, gameover, br);

	if (player1points > player2points) {
		char winner[40];
		sprintf_s(winner, "The winner is: Player 1 ");
		graphics::drawText(CANVAS_WIDTH / 2 - 150, CANVAS_HEIGHT / 2 - 30, 50, winner, br);
	}
	else {
		char winner[40];
		sprintf_s(winner, "The winner is: Player 2 ");
		graphics::drawText(CANVAS_WIDTH / 2 - 150, CANVAS_HEIGHT / 2 - 30, 50, winner, br);
	}
	char output[40];
	sprintf_s(output, "Press ENTER to play again ");
	graphics::drawText(CANVAS_WIDTH / 2 - 75, CANVAS_HEIGHT / 2 + 75, 30, output, br);

	char output2[40];
	sprintf_s(output2, "Press ESCAPE to exit ");
	graphics::drawText(CANVAS_WIDTH / 2 - 75, CANVAS_HEIGHT / 2 + 125, 30, output2, br);
}
void Game::init()
{
	graphics::setFont(std::string(ASSET_PATH) + "Pong.otf");// to font gia to score 
}

Game::Game()
{
}

Game::~Game()
{
	if (player) {
		delete player;
	}
	if (player2) {
		delete player2;
	}
	if (ball) {
		delete ball;
	}
	if (barrier) {
		delete barrier;
	}

}