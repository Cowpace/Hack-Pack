//arr[] is clockwise ordered simple polygon
boolean containsPt(pt arr[], pt point)
{
	
	int j = arr.length-1;
	
	for(int i = 0; i <arr.length; i++)
	{
		if((arr[i].y > point.y) != (arr[j].y > point.y) &&
				(point.x < (arr[j].x - arr[i].x)*(arr[j].y - arr[i].y)/
					(arr[j].x - arr[i].x)*(arr[j].y - arr[i].y)
						return true;
		j = i; //to loop j
	}
	
	return false;
}
