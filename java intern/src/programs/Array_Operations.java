package programs;



import java.util.*;

public class Array_Operations {
  static  int n;
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);

        while(true)
        {
            try {
                System.out.println("enter the number");
                n = Integer.parseInt(sc.nextLine().trim());
                break;
            }
            catch (NumberFormatException e)
            {
                System.out.println("enter valid number");


            }
        }
        int arr[]=new int[n];

        for(int i=0;i<n;i++)
        {
            int element;
            while(true)
            {
                try {
                    System.out.println("enter the number");
                    element=Integer.parseInt(sc.nextLine().trim());
                    break;
                }
                catch (NumberFormatException e)
                {
                    System.out.println("enter valid number");


                }
            }
            arr[i]=element;
        }
        int[] arrCopy=arr;
        System.out.println("Copied Array:"+Arrays.toString(arrCopy));
        sort(0,n-1,arr);
        System.out.println("Ascending Order"+Arrays.toString(arr));
        reverse(arr);
        System.out.println("Descending Order:"+Arrays.toString(arr));
        System.out.println("Duplicates:");
        frequency(arr);






    }
    public static void sort(int start,int end, int arr[])
    {
        if(start>=end)
            return;

            int mid=start+(end-start)/2;
            sort(start,mid,arr);
            sort(mid+1,end,arr);
            merge(start,mid,end,arr);
    }
    public static void merge(int start,int mid,int end,int[] arr)
    {
        int i=start,j=mid+1,k=start;
        int temp[]=new int[n];
        while(i<=mid&&j<=end)
        {
            if(arr[i]<arr[j])
            {
                 temp[k++]=arr[i++];
            }
            else
                temp[k++]=arr[j++];
        }
        while(i<=mid)
            temp[k++]=arr[i++];
        while(j<=end)
            temp[k++]=arr[j++];
        for(int l=start;l<=end;l++)
            arr[l]=temp[l];

    }
    public static void reverse(int arr[])
    {
        int l=0,r=arr.length-1;
        while(l<r)
        {
            int temp=arr[l];
            arr[l]=arr[r];
            arr[r]=temp;
            l++;r--;
        }
    }
    public static void frequency(int arr[])
    {
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<arr.length;i++)
        {
            if(!map.containsKey(arr[i]))
                map.put(arr[i],1);
            else
                map.put(arr[i],map.get(arr[i])+1);
        }
        List<List<Integer>> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            ArrayList<Integer> row = new ArrayList<>();
            row.add(entry.getKey());   // Store number first
            row.add(entry.getValue()); // Store frequency second

            list.add(row);
        }
        list.sort(Comparator.comparingInt(a -> a.get(0)));

        // Printing the result
        for (List<Integer> row : list) {
            System.out.println(row.get(0) + " appears " + row.get(1) + " times.");
        }
}
}
