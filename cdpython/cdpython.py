def make_path(p1, p2):
    # creates a list of the elements in the new path based on p1 and p2,
    # where "." means staying in the current directory, ".." means returning
    # to the previous directory, and "/" means going to the root directory
    if p2[0] == '/' and p2[-1] == '/':
        parts = p2[:(len(p2) - 1)].split("/")
    elif p2[0] == '/':
        parts = p2.split("/")
    elif p2[-1] == '/':
        parts = (p1 + "/" + p2[:(len(p2) - 1)]).split("/")
    else:
        parts = (p1 + "/" + p2).split("/")

    final_path = []
    for p in parts:
        if p == "":
            if p == parts[-1]:
                final_path.clear()
        elif p == ".":
            pass
        elif p == "..":
            if final_path:
                final_path.remove(final_path[-1])
        elif p in final_path:
            del final_path[final_path.index(p) + 1:len(final_path)]
        else:
            final_path.append(p)
    else:
        # creates a path and checks if it is made up of valid character, returning the path if it
        # does or an error message if it doesn't
        if final_path:
            new_path_str = ""
            for s in final_path:
                if s.isalnum() or s == "." or s == "..":
                    new_path_str = new_path_str + "/" + s
                else:
                    new_path_str = p2 + ": No such file or directory"
            return new_path_str
        else:
            return "/"


# Takes in two inputs from the command line with path1 representing the
# current directory and path2 representing the new directory, exits the
# program if the number of inputs is incorrect
def main():
    try:
        path1, path2 = input().split()
    except ValueError:
        print("Too many/few inputs")
        exit()
    print(make_path(path1, path2))


if __name__ == "__main__":
    main()

