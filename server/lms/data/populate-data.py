# sample runs: 
# python populate-data.py --metadata-path metadata/create-course-metadata.json --data-path sources/create-course-test.csv --hostname http://localhost:8080

import argparse
import csv
import json
import requests


def populate_data(hostname, metadata_filepath, data_file_path, course_id):
    with open(metadata_filepath, "r") as file:
        metadata = json.loads(file.read())

    with open(data_file_path, mode="r") as file:
        data = list(csv.DictReader(file))

    request_url = create_request_url(hostname, metadata["requestUri"], course_id)

    request_template = metadata["requestTemplate"]
    template_variables = metadata["templateVariables"]
    request_body = create_request_body(request_template, template_variables, data, course_id)

    send_request(request_url, request_body)


def create_request_url(hostname, request_uri, course_id):
    if course_id is None and "{course-id}" in request_uri:
        raise Exception("Course id is required")
    populated_uri = request_uri.replace("{course-id}", course_id) if course_id is not None else request_uri
    return hostname + populated_uri


def create_request_body(request_template, template_variables, data, course_id):
    for template_variable in template_variables:
        structured_data = []
        for data_element in data:
            value_string = json.dumps(template_variable["value"])
            for variableName, variableValue in data_element.items():
                lookup = "{{" + variableName + "}}"
                value_string = value_string.replace(lookup, variableValue)
            if course_id is not None:
                value_string = value_string.replace("{{classID}}", course_id)
                value_string = value_string.replace("{{courseID}}", course_id)
            print(value_string)
            structured_data.append(json.loads(value_string))
        request_template[template_variable["key"]] = structured_data
    return request_template


def send_request(request_url, request_body):
    headers = {"Content-type": "application/json"}
    response = requests.post(request_url, json.dumps(request_body), headers=headers)
    print(f"Request body: {request_body}")
    print(response)
    print(f"Response status code: {response.status_code}")
    print(f"Response content: {response.content}")


if __name__ == '__main__':
    parser = argparse.ArgumentParser(description="...")
    parser.add_argument("--metadata-path", type=str)
    parser.add_argument("--data-path", type=str)
    parser.add_argument("--hostname", type=str)
    parser.add_argument("--course-id", required=False, type=str)
    args = parser.parse_args()
    print(args)
    hostname = args.hostname
    metadata_file_path = args.metadata_path
    data_file_path = args.data_path
    course_id = args.course_id

    # metadata_file_path = "metadata/create-course-metadata.json"
    # hostname = "http://localhost:8080"
    # data_file_path = "sources/create-course-test.csv"
    populate_data(hostname, metadata_file_path, data_file_path, course_id)
