def function(n):
    if n<1:
        return 1
    else:
        return function(n-1)*n
print(function(int(input())))