#include<stdio.h>
#include<sys/socket.h>
#include<sys/types.h>
#include<netinet/in.h>
#include<string.h>

int main(int argc,char **argv)
{
	int sockfd;
	struct sockaddr_in servaddr;
	char str[1024],buff[1024];
	sockfd=socket(AF_INET,SOCK_STREAM,0);
	if(sockfd < 0)
		perror("\nsocket error\n");
	bzero(&servaddr,sizeof(servaddr));
	servaddr.sin_family=AF_INET;
	servaddr.sin_addr.s_addr=inet_addr(argv[1]);
	servaddr.sin_port=htons(5000);
	connect(sockfd,(struct sockaddr *)&servaddr,sizeof(servaddr));
	printf("Enter a Message:");
	gets(str);
	write(sockfd,str,sizeof(str));
	read(sockfd,buff,sizeof(buff));
	printf("Message Echoed: %s",buff);
	return 0;
	exit(0);
}

