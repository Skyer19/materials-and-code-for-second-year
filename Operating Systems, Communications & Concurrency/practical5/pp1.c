#include <fcntl.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>

main(int argc)
{
	int namedpipe;
	int max_size = 20;
	char message[max_size + 1];
	char buffer[max_size + 1];

	/* Check for existence of named pipe and create it if it doesn't exist */

	int i;
	namedpipe = open("fifo", O_RDONLY);
	for (i = 1; i <= 10; i++)
	{
		/* Reader will read 10 timesfrom pipe */
		int n = read(namedpipe, buffer, max_size);
		buffer[n] = 0;
		printf("reader: Read message <%s> %d characters \n", buffer, n);
	}
	close(namedpipe);
}
