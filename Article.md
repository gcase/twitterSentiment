Although social media has enabled consumers and businesses to connect like never before, it offers its own set of challenges.  The sheer flood of information makes it difficult to perform informed decisions. For instance, how is the brand manager of Acme Corp supposed to handle the launch of the new "WonderWidget" line?   There could be tens of thousands of tweets that need analyzing.  Not to mention Facebook comments, YouTube, Pinterest and Amazon reviews.   This is where Sentiment Analysis comes in.

Sentiment Analysis is the process of taking a block of text and determining if the author feels positive, neutral, or negative about a particular topic. It can be an extremely difficult problem to do correctly.   For instance, consider the following (naive) approach.   This approach takes simply takes a dictionary of words, assigning a positive or negative weight to each.  To determine the overall sentiment of a phrase, simply add up the scores of the words found in the dictionary.   Here is an example:

|Keyword  			| Sentiment |
|---------------|----------:|
| Excellent	    | 10        | 
| Impressed	    |  6        |
| Great        	|  5        |
| OK            |  1        |
| Meh           |  0        |
| Boring        |  -3       |
| Terrible      |  -5       |
| Sick          | -4        |

Now, taking this table, we can assign values to the following sentences.
_The movie was *great*!  *Excellent* explosions!_   5 + 10 = 15
_I though the movie was *terrible*.  *Boring*!_   -5 + -3  = -8

So far so good, for these completely contrived and unrealistic examples.  But notice how quickly we run into trouble.  For instance, the word “sick” can mean different things in context:
_I got *sick* after I ate Taco Bell._
_These rhymes are *sick*!_
Our naïve approach would treat both of these as negative sentiments, even though the latter is expressing approval of some “sick rhymes”.
Additional, the subject of phrase greatly affects how what a positive viewpoint means.  For instance, I’d love to read an unpredictable mystery novel, but would stay away from a car with unpredictable brakes.

Clearly the dictionary approach has some significant limitations.  In order to do a proper job of sentiment analysis, some natural language processing needs to be done.  Natural language processing takes human input, whether textual or spoken, and converts it to a form where a computer can infer some meaning.    However, going into the methods and science of natural language processing is beyond the scope of this article, and frankly, well beyond the understanding of this author.   The good news is, there are several companies that provide this service. 
