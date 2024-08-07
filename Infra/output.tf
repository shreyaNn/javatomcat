output "instance_id" {
  description = "The ID of the EC2 instance"
  value       = aws_instance.default.id
}
output "instance_public_ip" {
  description = "Public IP address of the EC2 instance"
  value       = aws_instance.default.public_ip
}
