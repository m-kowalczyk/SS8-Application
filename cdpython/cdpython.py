from pathlib import Path

# Takes in two inputs from the command line with path1 representing the
# current directory and path2 representing the new directory, exits the
# program if the number of inputs is incorrect
try:
    path1, path2 = input().split()
except ValueError:
    print("Too many/few inputs")
    exit()


def make_path(p1, p2):
    # creates a list of the elements in the new path based on p1 and p2,
    # where "." means staying in the current directory, ".." means returning
    # to the previous directory, and "/" means going to the root directory
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
        # creates a path and checks if it exists, returning the path if it
        # does or an error message if it doesn't
        if final_path:
            new_path_str = final_path[0]
            for p in final_path[1:]:
                new_path_str = new_path_str + "/" + p
            new_path = Path(new_path_str)
            if new_path.exists() and ("." not in new_path_str):
                return new_path
            else:
                return path2 + ": No such file or directory"
        else:
            return Path("/")


print(make_path(path1, path2))
