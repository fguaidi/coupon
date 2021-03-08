# Coupon 


### TEST

For localhost test:

Donwload the repo
```git
git clone origin https://github.com/fguaidi/coupon.git
```

Install [Redis](https://redis.io/topics/quickstart)

Import maven project and run. 

Execute the next curl:

```sh
curl --location --request POST 'localhost:5000/coupon/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "item_ids": [
        "MCO453165628",
        "MCO561290822",
        "MCO594954126",
        "MCO602224685",
        "MCO599620447"
    ],
    "amount": 50000
}'
```

For prod test execute:

```sh
curl --location --request POST 'http://couponservice-env.eba-29nrypvz.us-east-1.elasticbeanstalk.com/coupon/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "item_ids": [
        "MCO453165628",
        "MCO561290822",
        "MCO594954126",
        "MCO602224685",
        "MCO599620447"
    ],
    "amount": 50000
}'
```