#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<netinet/in.h>
#include<sys/socket.h>
#include<sys/types.h>
int main()
{
  FILE *fp;
  char buf[100],msg[100],buf1[100];
  struct sockaddr_in ser,cli;
  int sock,n,r,b;
  if((sock=socket(PF_INET,SOCK_DGRAM,0))<0)
  {
     perror("Error in socket creation");
     return 0;
  }
  printf("\nSocket created successfully");
  bzero(&ser,sizeof(ser));
  ser.sin_family=PF_INET;
  ser.sin_port=42523;
  ser.sin_addr.s_addr=htonl(INADDR_ANY);
  printf("\nbefore bind");
  b=bind(sock,(struct sockaddr *)&ser,sizeof(ser));
  printf("\nb=%d",b);
  if(b<0)
  {
     perror("\nSocket failed to bind");
     return 0;
  }
  printf("hello1");
  printf("\nSocket binded successfully");
  printf("bye");
  n=sizeof(cli);
  printf("%d",n);
  while(1)
  {
  	strcpy(buf1,"");
        fp=fopen("dns.txt","r");
        r=recvfrom(sock,buf,sizeof(buf),0,(struct sockaddr *)&cli,&n);
	
	printf("\nhello");
        while(!feof(fp))
        {
	  fscanf(fp,"%s",msg);
	  if(strcmp(msg,buf)==0)
          {
             fscanf(fp,"%s",buf1);
	     break;
          }
        }
        if(strcmp(buf1,"")==0)
	   strcpy(buf1,"Invalid address");
	fclose(fp);
	printf("%s",buf1);
	sendto(sock,buf1,sizeof(buf1),0,(struct sockaddr *)&cli,n);
	if((strcmp(buf,"exit"))==0)
	{
		printf("sever Exit...\n");
		break;
	}
   }
   close(sock);
   return 0; 
}
