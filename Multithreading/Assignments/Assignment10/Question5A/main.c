#include<stdio.h>
#include<stdlib.h>

struct Node
{
    int data;
    int id;
    char *firstname;
    char *lastName;
    char *course;
    struct Node *next;
}*front = NULL,*rear = NULL;

void insert(int,int, char*,char*,char*);
//void insert(int);
void delete();
void display();

void main()
{
    int choice, value ,id;
    char *firstname,*lastName,*course ;

    printf("\n:: Queue Implementation using Linked List ::\n");
    while(1){
        printf("\n****** MENU ******\n");
        printf("1. Insert\n2. Delete\n3. Display\n4. Exit\n");
        printf("Enter your choice: ");
        scanf("%d",&choice);
        switch(choice){
            case 1:
            {
                printf("Enter the value to be insert: ");
                scanf("%d", &value);
                printf("Enter id: ");
                scanf("%d", &id);
                printf("Enter firstname: ");
                scanf("%s", &firstname);
                printf("Enter lastname: ");
                scanf("%s", &lastName);
                printf("Enter course: ");
                scanf("%s", &course);

                insert(value,id, &firstname, &lastName,&course);
                break;
            }
            case 2: delete(); break;
            case 3: display(); break;
            case 4: exit(0);
            default: printf("\nWrong selection!!! Please try again!!!\n");
        }
    }
}
void insert(int value,int id,char* firstname,char* lastname,char* course)
{
    struct Node *newNode;
    newNode = (struct Node*)malloc(sizeof(struct Node));
    newNode->data = value;
    newNode->id = id;
    newNode->firstname = firstname;
    newNode->lastName = lastname;
    newNode->course = course;
    newNode -> next = NULL;
    if(front == NULL)
        front = rear = newNode;
    else{
        rear -> next = newNode;
        rear = newNode;
    }
    printf("\nInsertion is Success!!!\n");
}
void delete()
{
    if(front == NULL)
        printf("\nQueue is Empty!!!\n");
    else{
        struct Node *temp = front;
        front = front -> next;
        printf("\nDeleted element: %d\n", temp->data);
        free(temp);
    }
}
void display()
{

    if(front == NULL)
        printf("\nQueue is Empty!!!\n");
    else{
        struct Node *temp = front;
        while(temp->next != NULL){
            printf("{value: %d , id : %d ,firstname: %s ,lastName: %s,course: %s } ->\n",temp->data,temp->id,temp->firstname,temp->lastName,temp->course);

            //          printf("id  --->%d",temp->id);
         //   printf("firstName  --->%d",temp->firstname);
            temp = temp -> next;
        }
        printf("{value: %d , id : %d ,firstname: %s ,lastName: %s,course: %s } --->NULL\n",temp->data,temp->id,temp->firstname,temp->lastName,temp->course);
    }
}