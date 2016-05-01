//arr[] is clockwise ordered simple polygon
double PolygonArea(pt arr[])
{
	double area = 0;
	int j = arr.length-1;
	
	for(int i = 0; i <arr.length; i++)
	{
		area += (arr[i].x + arr[j].x) * (arr[i].y - arr[j].y);
		j = i; //to loop j
	}
	
	return area/2;
}
