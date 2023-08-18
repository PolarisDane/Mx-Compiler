#include <string.h>
#include <stdio.h>
#include <stdlib.h>

void print(char *s) {
    printf("%s", s);
}

void println(char *s) {
    printf("%s\n", s);
}

void printInt(int x) {
    printf("%d", x);
}

void printlnInt(int x) {
    printf("%d\n", x);
}

char* getString() {
  char* s = malloc(1 << 8);
  scanf("%s", s);
  return s;
}

int getInt() {
  int x;
  scanf("%d", &x);
  return x;
}

char* toString(int x) {
  char* s = malloc(1 << 4);
  sprintf(s, "%d", x);
  return s;
}

char* string__substring(char* s, int l, int r) {
  char* t = malloc(r - l + 1);
  for (int i = l; i < r; i++) t[i - l] = s[i];
  t[r - l] = '\0';
  return t;
}

int string__parseInt(char *s) {
  int x;
  sscanf(s, "%d", &x);
  return x;
}

int string__ord(char* s, int x) {
    return s[x];
}

char* string__stradd(char* s, char* t) {
  char* p = malloc(strlen(s) + strlen(t) + 1);
  strcpy(p, s);
  strcat(p, t);
  return p;
}

char string__strlt(char* s, char* t) {
    return strcmp(s, t) < 0;
}

char string__strle(char* s, char* t) {
    return strcmp(s, t) <= 0;
}

char string__strgt(char* s, char* t) {
    return strcmp(s, t) > 0;
}

char string__strge(char* s, char* t) {
    return strcmp(s, t) >= 0;
}

char string__streq(char* s, char* t) {
    return strcmp(s, t) == 0;
}

char string__strneq(char* s, char* t) {
    return strcmp(s, t) != 0;
}

char* __malloc(int size) {
    return malloc(size);
}

int __size(char* s) {
    return *(s - 4);
}


