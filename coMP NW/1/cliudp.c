#include<stdio.h>
#include<stdlib.h>
#include<sys/types.h>
#include<netinet/in.h>
#include<sys/socket.h>
void main()
{
	struct sockaddr_in ser,cli;
	int sock,n;
  	char buf[100],buf1[100];
	if((sock=socket(PF_INET,SOCK_DGRAM,0))<0)
	{
	 	perror("Error in creating sockets");
		exit(0);
	}
	printf("\nSocket created successfully");
	ser.sin_family=PF_INET;
	ser.sin_port=42523;
	ser.sin_addr.s_addr=htonl(INADDR_ANY);
	n=sizeof(ser);
	printf("\nEnter the canonical address");
	scanf("%s",buf);
/*  	write(sock,buf,sizeof(buf));
	read(sock,buf1,sizeof(buf1));
			if((strncmp(buff,"exit",4))==0)
		{
			printf("Client Exit...\n");
			break;
		}
*/
	sendto(sock,buf,sizeof(buf),0,(struct sockaddr *)&ser,n);
	recvfrom(sock,buf1,sizeof(buf1),0,(struct sockaddr *)&ser,&n);
	printf("\n%s",buf1);
	if((strcmp(buf,"exit")==0))
	{
		printf("Client exit");
	}
 	close(sock);
}
