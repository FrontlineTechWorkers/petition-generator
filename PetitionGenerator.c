#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <time.h>
#include <assert.h>
#include <string.h>

typedef struct {
    char **content;
    size_t size; // allocated memory
    size_t num; // actual number of items stored
} DynArr;

DynArr *dyn_arr_new(void) {
    DynArr *arr = malloc(sizeof *arr);
    assert(arr);
    arr->content = NULL;
    arr->size = 0;
    arr->num = 0;
    return arr;
}

void dyn_arr_free(DynArr *arr) {
    size_t i;
    for(i = 0; i < arr->num; i++) {
        free(arr->content[i]);
    }
    free(arr->content);
    free(arr);
}

void dyn_arr_append(DynArr *arr, const char *str) {
    if(arr->size == 0) {
        // allocate new memory
        arr->size = 8;
        arr->content = malloc(arr->size * sizeof *(arr->content));
        assert(arr->content);
    } else if(arr->num == arr->size) {
        // expand existing array
        arr->size *= 2;
        char **newcont = realloc(arr->content, arr->size * sizeof *newcont);
        assert(newcont);
        arr->content = newcont;
    }
    // store a new copy of str
    char *cpy = malloc(strlen(str) + 1);
    assert(cpy);
    strcpy(cpy, str);
    arr->content[arr->num] = cpy;
    arr->num++;
}

inline char *dyn_arr_get(DynArr *arr, size_t pos) {
    return (pos < arr->num ? arr->content[pos] : NULL);
}

char *utf8idx(char *str, size_t utf8char) {
    // Returns the pointer to the (utf8char-1)th character
    size_t count = 0;
    for(; *str; str++) {
        if((*str & 0xc0) != 0x80) { // utf8 characters detection
            count++;
            if(count == utf8char + 1) {
                return str;
            }
        }
    }
    return NULL;
}

inline void usage(char *prog_name) {
    fprintf(stderr, "Usage: %s rawname.txt count\n", prog_name);
}

int main(int argc, char *argv[]) {
    long count;
    // arguments validation
    if(argc != 3) {
        usage(argv[0]);
        return 1;
    }
    char *endptr;
    errno = 0;
    count = strtol(argv[2], &endptr, 10);
    if(endptr == argv[2] || *endptr || errno || count < 0) {
        usage(argv[0]);
        return 2;
    }
    FILE *f = fopen(argv[1], "r");
    if(!f) {
        fprintf(stderr, "%s: Unable to access file\n", argv[1]);
        usage(argv[0]);
        return 3;
    }
    // read name list file and store names in dynamic arrays
    size_t len = 256;
    char *buf = malloc(len);
    assert(buf);
    DynArr *firstnames = dyn_arr_new();
    DynArr *lastnames = dyn_arr_new();
    while(fgets(buf, len, f)) {
        size_t slen = strlen(buf);
        while(buf[slen-1] != '\n' && slen == len - 1) {
            // line longer than allocated memory, resize buffer
            char *newbuf = realloc(buf, len * 2);
            assert(newbuf);
            buf = newbuf;
            fgets(buf + len - 1, len + 1, f);
            len *= 2;
            slen = strlen(buf);
        }
        if(buf[slen-1] == '\n') {
            buf[slen-1] = '\0'; // strip new line
        }
        // append lastnames to dynamic array
        char *cpy = malloc(strlen(buf) + 1);
        assert(cpy);
        strcpy(cpy, buf);
        if(strstr(cpy, "葉劉") == cpy) {
            // silently discard, do nothing
        } else {
            *utf8idx(cpy, 1) = '\0'; // truncate to one utf8 char
            dyn_arr_append(lastnames, cpy);
        }
        free(cpy);
        // append first names to dynamic array
        size_t i = 1;
        char *tmpptr;
        while((tmpptr = utf8idx(buf, i))) {
            cpy = malloc(strlen(buf) + 1);
            assert(cpy);
            strcpy(cpy, tmpptr);
            if((tmpptr = utf8idx(cpy, 1))) { // if there is a char following
                *tmpptr = '\0'; // truncate
            }
            dyn_arr_append(firstnames, cpy);
            free(cpy);
            i++;
        }
    }
    fclose(f);
    free(buf);
    // randomly generate names
    srand(time(NULL));
    while(count--) {
        size_t n1 = rand() % lastnames->num;
        printf("%s", dyn_arr_get(lastnames, n1));
        if(rand() % 100 < 3) {
            // 3% chance of double-char last name
            // ok... might be a bit weird when this happens to male names,
            // but who cares?
            size_t n1 = rand() % lastnames->num;
            printf("%s", dyn_arr_get(lastnames, n1));
        }
        size_t n2 = rand() % firstnames->num;
        printf("%s", dyn_arr_get(firstnames, n2));
        if(rand() % 100 >= 3) {
            // 3% chance of single-char first name
            size_t n3 = rand() % firstnames->num;
            printf("%s", dyn_arr_get(firstnames, n3));
        }
        putchar('\n');
    }
    dyn_arr_free(firstnames);
    dyn_arr_free(lastnames);
    return 0;
}
