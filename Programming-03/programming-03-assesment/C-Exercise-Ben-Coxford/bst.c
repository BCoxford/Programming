#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* Node Structure */
typedef struct node
{
    //Attributes for a node.
   int modNumber;
   char* modYear;
   char* modTitle;
   char* modSem;
   struct node* left;
   struct node* right;
} node;

typedef void (*callback)(node*);

//Create a node
node* createNode(char **data) {
   node *new_node = (node*)malloc(sizeof(node)); //Dynamically assigns memory for a new node.
   if(new_node == NULL) { //If null
       fprintf (stderr, "Out of memory\n");
       exit(1);
   }
    //Assigns the values to each variable.
   new_node->modNumber = atoi(data[0]);
   new_node->modTitle = data[1];
   new_node->modYear = data[2];
   new_node->modSem = data[3];
    //Sets pointers to NULL (No children)
   new_node->left = NULL;
   new_node->right= NULL;
   return new_node; //Return the new node.
}

//Comparing module number A vs Node B. Return 1 if B is less than A and -1 if A is less than B.
int nodeCompare(int a, int b) {
   if(b < a) {
       return 1;
   }
   else {
       return -1;
   }
}

//Inserts a new njode into the tree.
node* insertNode(node *root, char **data) { //root node and the data to be entered.
   if(root == NULL) { //If there is no root node (tree empty) create a new node as the root.
       root = createNode(data);
   }
   else {
       node* current = root; //Current node is the root.
       int b = atoi(data[0]); //Retrieves integer value of the mod number.
       int c = 0; //Flag (if position of node found == 1)

       while(c==0) { //While position of new node not found.
           int a = current->modNumber; //Set a to the mod number for root.
           if(nodeCompare(a, b) == 1) { //If new less than current
               if(current->left != NULL) { //Left pointer of left node is not null
                   current = current->left; //Update current to the left pointer node.
               }
               else {
                   c=1; //If left pointer is null, position has been found.
               }
           }
           else { //If new greater than current node
               if(current->right != NULL) { //If right node is not null
                   current = current->right; //Update current node to right pointer node.
               }
               else { //If right pointer is null, new position has been found.
                   c=1;
               }
           }
       }
       
       //At this point the current node is the last child node.

       int a = current->modNumber; //Get the mod number of the current node.
       if(nodeCompare(a, b) == 1) { //Compare last child node with new node. If less than create new node at the left pointer.
           current->left = createNode(data);
       }
       else {
           current->right = createNode(data); //If greater than create new node to right pointer.
       }
   }
   return root; //Return the root node.
}

//Display nodes in traversal order using recursion.
void display_tree(node* current)
{
    //If both right and left node not null.
   if(current->left != NULL && current->right != NULL) {
       //Print current, left ,and right module number.
       printf("%d(L:%d)(R:%d)\n", current->modNumber, current->left->modNumber, current->right->modNumber);
       //Traverse each subtree (left side first)
       display_tree(current->left);
       display_tree(current->right);
   }
    //If left node is null
   else if (current->left == NULL && current->right != NULL) {
       //Print current module number and the right module number.
       printf("%d(L:NULL)(R:%d)\n", current->modNumber, current->right->modNumber);
       //Traverse the right of the subtree
       display_tree(current->right);
   }
    //If right node is null
   else if (current->left != NULL && current->right == NULL) {
       //Print the current module number and the left module number.
       printf("%d(L:%d)(R:NULL)\n", current->modNumber, current->left->modNumber);
       //Traverse the left of the subtree.
       display_tree(current->left);
   }
   else {
       //If it is the last child node, print the current module number.
       printf("%d(L:NULL)(R:NULL)\n", current->modNumber);
   }
}

/* Load Data */
node* readData() {
   FILE *f;
   char* line = malloc(400); /*Dynamically adds memory for a line*/
   f = fopen("pg03data.txt", "r");
   node* root = NULL;
   while (fgets(line, 400, f) != NULL) { //Retrieves each line
       char* parse;
       int current = 0;
       char *arrayStr[4]; //Array holding data
       parse = strtok(line, ","); //Tokenize the data
       while (parse != NULL) {
           arrayStr[current] = malloc(strlen(parse)+1); //Allocate memory.
           strcpy(arrayStr[current], parse); //Copy the token to the array.
           parse = strtok(NULL, ","); //Next token.
           current++; //Increment array position. 
       }
       if(root == NULL) { //If no child node, create a root node.
           root = insertNode(root, arrayStr);
       }
       else {
           insertNode(root, arrayStr); //Create new node.
       }
   }
   fclose(f);
   return root; //Return the root node.
}

//Write data to a file.
void writeData(node* current) {
   FILE *f;

   f = fopen("dataSorted.txt", "a"); //Append to end file.

   char modNum[20];

   sprintf(modNum, "%d", current->modNumber); //Convert the modnumber to string/array of characters.

   char* data = malloc(400); //Allocate memory for the data.

    //Reformat the data to be written.
   strcpy(data, modNum);
   strcat(data, ",");
   strcat(data, current->modTitle);
   strcat(data, ",");
   strcat(data, current->modYear);
   strcat(data, ",");
   strcat(data, current->modSem);

   fprintf(f, "%s", data); //Write data

   fclose(f); //Close file.
}

//Traverse the tree in order traversal.
void inOrder(node* current) {
   if(current == NULL) {
        return;  
   }

   if(current->left != NULL) {
       inOrder(current->left);
   }

   writeData(current);

   if(current->right != NULL) {
       inOrder(current->right);
   }
}

int main(int argc, char *argv[]) {
   node* root = readData();
   display_tree(root);
   inOrder(root);
}
