#include <stdio.h>
#include <unistd.h>

int main()
{
	char buffer[10];
	int n;

	printf("Enter 6 Characters\n");
	n = read(0, buffer, 6);

	printf("%d characters were entered\n",n);
	printf("The characters were\n");
	write(1,buffer,n);
}
