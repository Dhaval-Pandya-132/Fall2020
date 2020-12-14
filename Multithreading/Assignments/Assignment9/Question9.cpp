//
// Created by Dhaval Pandya on 11/21/20.
//

#include "Question9.h"
#include <iostream>

pthread_mutex_t mutex1 = PTHREAD_MUTEX_INITIALIZER;
int  counter = 0;

void *print_message_function( void *ptr )
{
char *message;
message = (char *) ptr;
printf("%s \n", message);

}

void *print_message_function_mutex( void *ptr )
{
//    pthread_mutex_lock( &mutex1 );
    counter++;
    printf("Counter value: %d\n",counter);
//    pthread_mutex_unlock( &mutex1 );

}

int question9_mutex(){
    int rc1, rc2;
    pthread_t thread1, thread2;

    /* Create independent threads each of which will execute functionC */

    if( (rc1=pthread_create( &thread1, NULL, &print_message_function_mutex, NULL)) )
    {
        printf("Thread creation failed: %d\n", rc1);
    }

    if( (rc2=pthread_create( &thread2, NULL, &print_message_function_mutex, NULL)) )
    {
        printf("Thread creation failed: %d\n", rc2);
    }

    /* Wait till threads are complete before main continues. Unless we  */
    /* wait we run the risk of executing an exit which will terminate   */
    /* the process and all threads before the threads have completed.   */

    pthread_join( thread1, NULL);
    pthread_join( thread2, NULL);

    exit(EXIT_SUCCESS);
}


int question9(){
    pthread_t thread1, thread2;
    const char *message1 = "Thread 1";
    const char *message2 = "Thread 2";
    int  iret1, iret2;
    /* Create independent threads each of which will execute function */
    iret1 = pthread_create( &thread1, NULL, print_message_function, (void*) message1);
    if(iret1)
    {
        fprintf(stderr,"Error - pthread_create() return code: %d\n",iret1);
        exit(EXIT_FAILURE);
    }
    iret2 = pthread_create( &thread2, NULL, print_message_function, (void*) message2);
    if(iret2)
    {
        fprintf(stderr,"Error - pthread_create() return code: %d\n",iret2);
        exit(EXIT_FAILURE);
    }


    printf("pthread_create() for thread 1 returns: %d\n",iret1);
    printf("pthread_create() for thread 2 returns: %d\n",iret2);

    pthread_join( thread1, NULL);
    pthread_join( thread2, NULL);

    exit(EXIT_SUCCESS);

}