#include<stdio.h>
#include<stdlib.h>
#include<sys/types.h>
#include<netinet/in.h>
#include<sys/socket.h>
void main()
{
	struct sockaddr_in ser,cli;
	int sock,n,choice,r;
  	char buf[100],buf1[100],c1[]="a",c2[]="b";
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
        printf("\nEnter your choice:\n1.ARP\t2.RARP");
	scanf("%d",&choice);
        if(choice==1){
	 printf("\nEnter the IP address");
	 scanf("%s",buf);
	 sendto(sock,c1,sizeof(c1),0,(struct sockaddr *)&ser,n);
	 sendto(sock,buf,sizeof(buf),0,(struct sockaddr *)&ser,n);
	 r=recvfrom(sock,buf1,sizeof(buf1),0,(struct sockaddr *)&ser,&n);
	 printf("\n%s",buf1);
	 if((strcmp(buf,"exit")==0))
	 {
		printf("Client exit");
	 }
        }
	else{
	 printf("\nEnter the MA C address");
	 scanf("%s",buf);
	 sendto(sock,c2,sizeof(c2),0,(struct sockaddr *)&ser,n);
	 sendto(sock,buf,sizeof(buf),0,(struct sockaddr *)&ser,n);
 	 r=recvfrom(sock,buf1,sizeof(buf1),0,(struct sockaddr *)&ser,&n);
	 printf("\n%s",buf1);
	 if((strcmp(buf,"exit")==0))
		printf("\n Client exit");
	}
 	close(sock);
}

