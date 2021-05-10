#include <stdio.h>
#include <fcntl.h>
#include <unistd.h>

main()
{

/* Add code here to declare variables used, then to ask user for a file name
 to read, and to open that file for reading */
	char buffer[10];
	char name[10];
	int fd ,n;

	printf("enter your file name\n");
	scanf("%s", name);
	fd = open(name,O_RDONLY);


	do {
		n = read(fd, buffer, 10);
		write(1, buffer, n);
		} while (n==10);

/* Add code to close the file */
close(fd);
}
