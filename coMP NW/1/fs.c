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
    int sockfd, newfd, len,n,i,j,fd; 
    char buff[80],temp[20];
    struct sockaddr_in servaddr, cli; 
  
    // create socket 
    sockfd = socket(AF_INET, SOCK_STREAM, 0); 
    if (sockfd == -1) { 
        printf("socket creation failed...\n"); 
        exit(0); 
    } 
    else
        printf("Socket successfully created..\n");
         
    bzero(&servaddr, sizeof(servaddr)); 
  
    servaddr.sin_family = AF_INET; 
    servaddr.sin_addr.s_addr = htonl(INADDR_ANY); 
    servaddr.sin_port = htons(7035); 
   
    if ((bind(sockfd, (struct sockaddr*)&servaddr, sizeof(servaddr))) != 0) { 
        printf("socket bind failed...\n"); 
        exit(0); 
    } 
    else
        printf("Socket successfully binded..\n"); 
  
     
    if ((listen(sockfd, 5)) != 0) { 
        printf("Listen failed...\n"); 
        exit(0); 
    } 
    else
        printf("Server listening..\n"); 
    
    len = sizeof(cli); 
    
    

    newfd = accept(sockfd, (struct sockaddr*)&cli, &len); 
    if (newfd < 0) { 
        printf("server acccept failed...\n"); 
        exit(0); 
    } 
    else
        printf("Server acccepted the client %d...\n",newfd); 	  
  
        
        read(newfd, buff, sizeof(buff)); 
        
        printf("\nFile to be transferred is: %s\n", buff); 
        
        fd = open (buff,O_RDONLY);
        if (fd == -1)
        	perror("\nError in opening file!");
        strcpy(buff,"");
        i=0;
        while (read (fd,temp,1)!=0)
        	{
        	i++;
        	strcat(buff,temp);
        	}
        buff[i]='\0';
        
        //n = 0; 
        
        //while ((buff[n++] = getchar()) != '\n') 
          //  ;    
        
        
        /*i=0;
        while (buff[i]!='\0')
        	{
			write (newfd, (buff+i), 1);
        	i++;
        	}
        printf ("\nFile transferred\n");*/
        
          write(newfd, buff, sizeof(buff)); 
          printf ("\nFile transferred\n");  
    close (fd); 
    close (newfd);  
    close(sockfd); 
} 

