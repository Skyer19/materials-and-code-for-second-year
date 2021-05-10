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
  bool paramerror = false;
  printf("master");
  /* Check for existence of named pipe and create it if it doesn't exist */
  if (access("fifo", F_OK) == -1)
  {
    if (mknod("fifo", 010600, 0) == 0)
    {
      printf("Name Pipe created successfully \n");
    }
    else
    {
      printf("Failed to created Name Pipe \n");
      exit(0);
    }
  }

  open("fifo", O_WRONLY);
  close(1);
  dup(3);
  printf("master");
}
