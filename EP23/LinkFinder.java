/******************************************************************************
 *  Compilation:  javac LinkFinder.java In.java
 *  Execution:    java LinkFinder url -s(optional)
 *  
 *  Downloads the web page and prints out all urls on the web page.
 *  Gives an idea of how Google's spider crawls the web. Instead of
 *  looking for hyperlinks, we just look for patterns of the form:
 *  http:// followed by any non-whitespace characters except ".
 *
 *  % java LinkFinder http://www.cs.princeton.edu
 *  http://www.w3.org/TR/REC-html40/loose.dtd 
 *  http://www.cs.princeton.edu/
 *  http://maps.yahoo.com/py/maps.py?addr=35+Olden+St&csz=Princeton,+NJ,+08544-2087
 *  http://www.princeton.edu/
 *  http://www.Princeton.EDU/Siteware/WeatherTravel.shtml
 *  http://ncstrl.cs.Princeton.EDU/
 *  http://www.genomics.princeton.edu/
 *  http://www.math.princeton.edu/PACM/
 *  http://libweb.Princeton.EDU/
 *  http://libweb2.princeton.edu/englib/
 *  http://search.cs.princeton.edu/query.html
 *
 ******************************************************************************/

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import edu.princeton.cs.algs4.*;

public class LinkFinder { 


   public static void main(String[] args) { 
      In in = new In(args[0]);
      String input = in.readAll();
	  String regexp = "(href=\"[[\\S]&&[^\"]]+\")|(\"http://|https://)[[\\S]&&[^\"]]+\"";
      Pattern pattern = Pattern.compile(regexp);
      Matcher matcher = pattern.matcher(input);
	  MinPQ<String> pq = new MinPQ<String>();
	  
	  boolean sort = false;
	  if (args.length > 1 && args[1].equals("-s"))
		  sort = true;
    
      // find and print all matches
      while (matcher.find()) {
		String output = matcher.group();
		if (output.matches("href=\"[[\\S]&&[^\"]]+\"")){
			output = output.substring(5).replaceAll("\"", "");
		}
		else
			output = output.replaceAll("\"", "");
		if (output.matches("(http://|https://)[[\\S]&&[^\"]]+")){
			if (sort)
				pq.insert(output);
			else
				System.out.println(output);
		}
		else{
			if (output.charAt(0) != '/')
				output = "/".concat(output);
			if (sort)
				pq.insert(args[0] + output);
			else
				System.out.println(args[0] + output);
		}
	  }
	  if (sort){
		  for (String out : pq){
			  System.out.println(out);
		  }
	  }
   }

}
