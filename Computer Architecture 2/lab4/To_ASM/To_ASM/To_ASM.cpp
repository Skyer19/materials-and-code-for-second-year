// To_ASM.cpp : �������̨Ӧ�ó������ڵ㡣
//

#include "stdafx.h"
#include <stdio.h>
int _tmain(int argc, _TCHAR* argv[])
{
register int total=0;
for(register int i=0;i<=20;i++)
{
total+=i;
}
printf("%d",total);
getchar();
return 0;
}

