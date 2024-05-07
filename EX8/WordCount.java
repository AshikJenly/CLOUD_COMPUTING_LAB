import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.io.*;

import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class WordCount
{
    public static class MapWords extends Mapper<LongWritable,Text,Text,IntWritable>
    {
        public void map(LongWritable key,Text value,Context cont) throws IOException,InterruptedException
        {
            String words[] = value.toString().split("\\s+");
            for (String word:words){
                cont.write(new Text(word),new IntWritable(1));

        }
        }
    }
    public static class ReducerWords extends Reducer <Text,IntWritable,Text,IntWritable>
    {
        public void reduce(Text key,Iterable<IntWritable> values,Context cont) throws IOException,InterruptedException
        {

            int sum = 0;
            for(IntWritable value:values)
                sum += value.get();
            
            cont.write(key,new IntWritable(sum));
        }
    }

    public static void main(String args[]) throws Exception
    {

        Configuration conf = new Configuration();
        Job job = new Job(conf,"example");


        job.setJarByClass(WordCount.class);

        job.setMapperClass(MapWords.class);
        job.setReducerClass(ReducerWords.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        
        FileInputFormat.addInputPath(job,new Path(args[0]));
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        System.exit(job.waitForCompletion(true)? 0 : 1);
    }
}