// part_3.cpp : 定义控制台应用程序的入口点。
//

#include "stdafx.h"
#include <stdio.h>
int _tmain(int argc, _TCHAR* argv[])
{
int *address; // Create a pointer that can store an address (location in memory)
int x=10; // Create an integer variable called x containing value 10
int y=0; // y is an int with an initial value 0
int z[3]={5,7,11}; // Create an array of three integers
address=&x; // Let address store the value of the address of x in memory
y=*address; // y is assigned the value of whatever is pointed to by address
address =&z[0]; // address is set to the location in memory of the first value
y+=*(address+2); //y is incremented by the value pointed to at two items above address
printf("%d\n",y); // prints y to screen (y=????)
// Wait for enter to be pressed before terminating
while(getchar()!=10); // Clear buffer of previous <ret>
while(getchar()!=10); // Wait for a new <ret>
return 0;
}