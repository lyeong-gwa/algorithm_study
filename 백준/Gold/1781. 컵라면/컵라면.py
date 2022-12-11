import heapq
import sys
read = sys.stdin.readline
TC = int(read())
input_list = []
select_value = []
for i in range(TC):
    input_list.append(list(map(int,read().split())))
input_list.sort(key=lambda x:(x[0]))

for timer, cnt in input_list:
    heapq.heappush(select_value,cnt)
    if len(select_value)>timer:
        heapq.heappop(select_value)
print(sum(select_value))