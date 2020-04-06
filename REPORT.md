# Crypto Lab – One-Way Hash Function and MAC
_Linus Bein Fahlander_\
_linusfa@kth.se_

**_This file can be read on the [public github](https://github.com/LinusBF/iv1013-hashing/blob/master/REPORT.md) repository with Markdown formatting._**

## 2.1 Generating Message Digest and MAC
### Q1
In general the only difference between the algorithms is how many bytes the resulting hash value has.
For MD5 it's 32, SHA1 40 and SHA256 64.
They all output hexadecimal strings and there seems to be no apparent pattern in the output between them.
### Q2
| Algorithm | Hash of "linusfa@kth.se" | Hash of "Random Check" |
|-----------|--------------------------|--------------------------|
| MD5 | 600644ce869d89155802dc8ec793ba8b | 64f3e7a60b0caf6d484864a008fee1ec |
| SHA1 | 7e11e00663830d50b92218fcb944b0227903c884 | b57a06da99bd578b125d33a2866ec589462be918 |
| SHA265 | 4d4efbf2343d8ae8df5165881a39e477dc9f0cd2d150251093b68bce4e378491 | 8c749eab3ecd372bd9e0b275a1693c2bda2bfa2cfe30c70cab1b56ff92983d9f |
## 2.2 Keyed Hash and HMAC
### Q3
No you do not, the command accepts any input larger than 0 characters.
According to the [RFC-2104](https://tools.ietf.org/html/rfc2104#section-3) specification it seems that a key of at least equal size to the hash output is recommended.

### Q4
| Algorithm | Hash of "linusfa@kth.se" with HMAC key "IV1013-key" |
|-----------|------------------------------------------------|
| MD5 | 3962184f5123692617a3dcff2f26dbdb |
| SHA1 | f55d702c32ce0ac021f9449e67bb93f384ee278c |
| SHA265 | d1ef8e2c6e32670de70ebef5104e5ad36cde42991752b219bcd9a314c26bff80 |
## 2.3 The Randomness of One-way Hash
### Q5
I wrote a program for calculating the bit difference between two strings in the BitDiffCalculator.java file.\
Running the digest of "linusfa@kth.se" and ",inusfa@kth.se" (Which is the resulting string when flipping the first bit) we can see a big difference in the two digests.\
The bit difference is higher than 50%, so while not uniformly random, I would say that the algorithms generate very random hash values indeed.

| Algorithm | Hash of "linusfa@kth.se" | Hash of ",inusfa@kth.se" | Bit difference |
|-----------|--------------------------|--------------------------|----------------|
| MD5 | 600644ce869d89155802dc8ec793ba8b | d1cb6bdfa40f6930defb68047b67bc62 | 79 (~62%) |
| SHA265 | 4d4efbf2343d8ae8df5165881a39e477dc9f0cd2d150251093b68bce4e378491 | c9abe0c7b0b89d9b4f9285d3adc7d4eed2ce278770d79bf0f8c2f65b8b5461d1 | 168 (~66%) |
## 2.4 Collision Resistance
### Q6
The strategy I used for brute forcing the first 24 bits of the digest is a loop counting from 0 to 2^24.\
The current loop value is then hashed and the first 24 bits are compared to the target hash.\
The loop breaks when the comparison is true.\
Below are the results of my program

| String | Number of tries to find collision in first 24 bits | Collision Message in Hex |
|--------|----------------------------------------------------|--------|
| "No way" | 13 867 827 | 0xffffffaf 0xffffffec 0x74 |
| "Secure IV1013" | 378 792 | 0x2 0xffffff83 0xffffffe3 |
| "Security is fun" | 164 836 | 0x6f 0x6a 0x9 |
| "IV1013 security" | 11 529 333 | 0x5 0xffffffc7 0xffffffa7 |
| "Yes, indeed" | 7 301 642 | 0xffffffd3 0xffffff9b 0x32 |
