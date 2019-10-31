#include<stdio.h>
#include<netinet/in.h>
#include<stdlib.h>
#include<string.h>
#include<sys/types.h>
#include<sys/socket.h>
void main()
{
  FILE *fp;
   char buf[100],msg[100],buf1[100],choice[5];
  struct sockaddr_in ser,cli;
  int sock,b,status,len,r,n,v;
  int pos,p;
  if((sock=socket(PF_INET,SOCK_DGRAM,0))<0)
  {
     perror("Error in socket creation");
     exit(0);
  }
  printf("\nSocket created successfully");
  bzero(&ser,sizeof(ser));
  ser.sin_family=PF_INET;
  ser.sin_port=42523;
  ser.sin_addr.s_addr=htonl(INADDR_ANY);
   b=bind(sock,(struct sockaddr *)&ser,sizeof(ser));
  if(b<0)
  {
     perror("\nSocket failed to bind");
     exit(0);
  }
  printf("\nSocket binded successfully");
  n=sizeof(cli);
  while(1)
  {
  	strcpy(buf1,"");
        fp=fopen("arp.txt","r");
	v=recvfrom(sock,choice,sizeof(choice),0,(struct sockaddr *)&cli,&n);
        r=recvfrom(sock,buf,sizeof(buf),0,(struct sockaddr *)&cli,&n);
        if((strcmp(choice,"a"))==0)
	{
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
	}
	else
	{
	 while(!feof(fp))
         {
	  pos=ftell(fp);
          fscanf(fp,"%s",msg);
          if(strcmp(msg,buf)==0)
          {
             p=pos-13;
	     fseek(fp,p,SEEK_SET);
             fscanf(fp,"%s",buf1);
             break;
          }
        }
        if(strcmp(buf1,"")==0)
           strcpy(buf1,"Invalid address");
	}
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
}
