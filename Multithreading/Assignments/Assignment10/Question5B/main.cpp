#include<stdio.h>
#include<stdlib.h>

struct Node
{
    int data;
    int id;
    char* firstname;
    char* lastName;
    char* course;
    struct Node *next;
}*top = NULL;

void push(int,int,char* ,char* ,char*);
void pop();
void display();

int main()
{
    int choice, value ,id;
    char *firstname,*lastName,*course ;
//    clrscr();
    printf("\n:: Stack using Linked List ::\n");
    while(1){
        printf("\n****** MENU ******\n");
        printf("1. Push\n2. Pop\n3. Display\n4. Exit\n");
        printf("Enter your choice: ");
        scanf("%d",&choice);
        switch(choice){
            case 1: printf("Enter the value to be insert: ");
                scanf("%d", &value);
                printf("Enter id: ");
                scanf("%d", &id);
                printf("Enter firstname: ");
                scanf("%s", &firstname);
                printf("Enter lastname: ");
                scanf("%s", &lastName);
                printf("Enter course: ");
                scanf("%s", &course);
                push(value, id, reinterpret_cast<char *>(&firstname), reinterpret_cast<char *>(&lastName),
                     reinterpret_cast<char *>(&course));
                break;
            case 2: pop(); break;
            case 3: display(); break;
            case 4: exit(0);
            default: printf("\nWrong selection!!! Please try again!!!\n");
        }
    }
    return 0;
}
void push(int value, int id,char* firstname,char* lastname,char* course)
{
    struct Node *newNode;
    newNode = (struct Node*)malloc(sizeof(struct Node));
    newNode->data = value;
    newNode->id = id;
    newNode->firstname = firstname;
    newNode->lastName = lastname;
    newNode->course = course;
    if(top == NULL)
        newNode->next = NULL;
    else
        newNode->next = top;
    top = newNode;
    printf("\nInsertion is Success!!!\n");
}
void pop()
{
    if(top == NULL)
        printf("\nStack is Empty!!!\n");
    else{
        struct Node *temp = top;
        printf("\nDeleted element: %d", temp->data);
        top = temp->next;
        free(temp);
    }
}
void display()
{
    if(top == NULL)
        printf("\nStack is Empty!!!\n");
    else{
        struct Node *temp = top;
        while(temp->next != NULL){
      //      printf("%d--->",temp->data);
            printf("{value: %d , id : %d ,firstname: %s ,lastName: %s,course: %s } ->\n",temp->data,temp->id,temp->firstname,temp->lastName,temp->course);

            temp = temp -> next;
        }
        printf("{value: %d , id : %d ,firstname: %s ,lastName: %s,course: %s } --->NULL\n",temp->data,temp->id,temp->firstname,temp->lastName,temp->course);
      //  printf("%d--->NULL",temp->data);
    }
}