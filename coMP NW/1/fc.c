#include <stdio.h>
#include <netinet/in.h>
#include <stdlib.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <fcntl.h>
  
void main() 
{ 
    int sockfd, connfd,n,j,fd,i;
    char buff[80],fname[20]; 
    struct sockaddr_in servaddr, cli; 
  
    
    sockfd = socket(AF_INET, SOCK_STREAM, 0); 
    if (sockfd == -1) { 
        printf("socket creation failed...\n"); 
        exit(0); 
    } 
    else
        printf("Socket successfully created..\n"); 
    bzero(&servaddr, sizeof(servaddr)); 
  
    
    servaddr.sin_family = AF_INET; 
    servaddr.sin_addr.s_addr = inet_addr("127.0.0.1"); 
    servaddr.sin_port = htons(7035); 
  
    
    if (connect(sockfd, (struct sockaddr*)&servaddr, sizeof(servaddr)) != 0) { 
        printf("connection with the server failed...\n"); 
        exit(0); 
    } 
    else
        printf("connected to the server..\n"); 
  
        
    
    	  bzero(buff, sizeof(buff)); 
        printf("\n\nEnter file path : "); 
        //n = 0; 
        scanf("%s",buff);
       
        write(sockfd, buff, sizeof(buff));
        sleep(4);
        read(sockfd, buff, sizeof(buff));
        
        
        
        printf ("\n\nFile transferred");
        printf ("\n\nEnter location to save file: ");
        scanf("%s",fname);
        fd = open(fname,O_WRONLY|O_CREAT);
         if (fd == -1)
        	perror("\nError in opening file!");
        i=0;
        while (buff[i]!='\0')
        	{
			write (fd, (buff+i), 1);
        	i++;
        	}
        
         
        //bzero(buff, sizeof(buff)); 
        //read(sockfd, buff, sizeof(buff)); 
        //printf("From Server : %s", buff); 

	 
    	  
  
    close(fd); 
    close(sockfd); 
} 

