Testing of List Breweries APIs

Each test has DataProvider with multiple datasets

1. One test to check positive cases for Query params: by_city, by_name, by_state, by_postal, by_type
2. One negative to check validations (response body is empty): by_type, by_postal, by_state
3. One positive to check combinations with all query params
4. One positive to check page limits and page offset
5. One positive to check sorting (DataProvider will provide fields for sorting and Comparator.comparing(Brewery::getName) to verify that received response sorted correctly)

Complexity: medium

Estimation: 8h