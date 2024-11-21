#pragma once


float rand0to1();// pairnw tyxaious arithmous apo 0 mexri 1
float randOper(float a, float b);
int rangeRandom(int min, int max);
float max(float a, float b);
struct Disk// structs poy tha bohthsoyn sta collisions
{
	float cx, cy;
	float radius;
};
struct Rectangle
{
	float rx, ry;
	float rw, rh;
};