## Concepts:
* **Credits**  - Arabic numerals
* **Intergalactic units** - Roman numerals
* **Resources** - 3 or ...As much as needs
* **Incorrect request**:
  * Unknown items(not determined)
  * Incorrect operation
  * Calculation of the cost when Intergalactic Units are not fully determined


## Operations:
1. **Initialization** of Intergalactic units (Roman numerals)
2. **Determining** the cost of resources.
3. **Calculation**
   1. Intergalactic units to Credits
   2. Resources to Credits


### Detailed description of the operation:
1. **Initialization** of Intergalactic units (Roman numerals)
* we have only 7 Roman numeric (I, V, X, L, C, D, M) and 7 unknown Intergalactic units
* the name of Intergalactic units must be lowercase.
* this operation must be the first.
* the first character is small letter.
* there should be only 3 words per line
* space separated
* middle word to be "is"
* the last one must be from the Roman numeric list
* must enter all 7 values in accordance with Roman numerals. (not user friendly, will improve later)
2. **Determining** the cost of resources
   * this operation must be the second.
   * the first word is one of the Intergalactic units.
   * "is" must be present
   * space separated
   * must end with "Credits"
   * must be a number after "is".
   * before "is" is the name of the resource
   * resource types can be unlimited(int)

3. **Calculation**
* must start with "how much" or "how many"
* "is" must be present
* must end with "?"
   1. Intergalactic units to Credits
   * must start with "how much"
   * the third word to be "is"
   * after "is" are only values IU

   2. Resources to Credits
   * must start with "how many Credits"
   * before "?" is the name of the resource
   * between "the name of the resource" and "is" any number of resource types
4. Algorithm for converting Roman numerals to Arabic
   * ???